package com.ms.blogserver.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ms.blogserver.core.constant.contexts.UrlContexts;
import com.ms.blogserver.utils.RedisUtils;
import com.ms.blogserver.utils.TokenUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @description: jwt 过滤器
 * @author: zhh
 * @time: 2021/5/24
 */

public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    private RedisUtils redisUtils;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 判断是否允许通过
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUrl = httpServletRequest.getRequestURI();
        if (!UrlContexts.WEBSOCKET.equals(requestUrl)){
            logger.info("\033[2;36m"+"请求方法:"+httpServletRequest.getMethod()+", 请求路径:["+httpServletRequest.getRequestURI()+"]"+" \033[0m");
        }
        if (this.isLoginAttempt(request,response)) {
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否进行登录请求
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token=((HttpServletRequest)request).getHeader("token");
        return token != null;
    }

    /**
     * 创建shiro token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        logger.debug("createToken方法");
        String jwtToken = ((HttpServletRequest)request).getHeader("token");
        if(jwtToken!=null) {
            return new JwtToken(jwtToken);
        }
        return null;
    }

    /**
     * isAccessAllowed为false时调用，验证失败
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request,response);
        responseError(response);
        return false;
        //throw new CustomAuthorizedException("sfrzyc");
    }

    /**
     * shiro验证成功调用
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        String jwtToken= (String) token.getPrincipal();
        if (jwtToken!=null){
            try{
                if(TokenUtils.verify(jwtToken)){
                    //判断Redis是否存在所对应的RefreshToken
                    String account = TokenUtils.getAccount(jwtToken);
                    Long currentTime= TokenUtils.getCurrentTime(jwtToken);
                    if (redisUtils.hasKey(account)) {
                        Long currentTimeMillisRedis;
                        Object o = redisUtils.get(account);
                        currentTimeMillisRedis= (Long) o;
                        return currentTimeMillisRedis.equals(currentTime);
                    }
                }
                return false;
            }catch (Exception e){
                logger.error("token验证："+e.getClass());
                if (e instanceof TokenExpiredException){
                    try {
                        return refreshToken(request, response);
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    }
                }
            }
        }
        return true;
    }



    /**
     * 拦截器的前置方法，此处进行跨域处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request,response);

    }


    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     * @param request
     * @param response
     * @return
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) throws UnsupportedEncodingException {
        String token = ((HttpServletRequest)request).getHeader("token");
        String account = TokenUtils.getAccount(token);
        Long currentTime= TokenUtils.getCurrentTime(token);
        // 判断Redis中RefreshToken是否存在
        if (redisUtils.hasKey(account)) {
            // Redis中RefreshToken还存在，获取RefreshToken的时间戳
            Long currentTimeMillisRedis = (Long) redisUtils.get(account);
            // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
            if (currentTimeMillisRedis.equals(currentTime)) {
                // 获取当前最新时间戳
                Long currentTimeMillis =System.currentTimeMillis();
                redisUtils.set(account, currentTimeMillis, TokenUtils.REFRESH_EXPIRE_TIME);
                // 刷新AccessToken，设置时间戳为当前最新时间戳
                token = TokenUtils.sign(account, currentTimeMillis);
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Authorization", token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                return true;
            }
        }
        return false;
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private void responseError(ServletResponse response) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.sendRedirect("/filter/error");
            logger.error("responseError:"+ "token verify fail");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
