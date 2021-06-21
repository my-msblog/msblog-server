package com.ms.blogserver.constant.controller;

import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.exception.ProgramException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Slf4j
public class BaseController {

    /**
     * 获取request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取请求token
     * @return
     */
    public String getHeaderToken(){
        return getRequest().getHeader("token");
    }

    /**
     * 获取客户端Ip
     *
     * @return ip
     */
    public String getClientIp(){
        HttpServletRequest request = getRequest();
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
    /**
     * 判断请求来源
     *
     * @return 结果
     */
    public String getTerminal(){
        HttpServletRequest request = getRequest();
        String terminal = request.getHeader("User-Agent");
        if(terminal.contains("Windows NT")){
            terminal = "pc";
        }else{
            terminal = "mobile";
        }
        return terminal;
    }

    /**
     * 异常判断
     *
     * @param e
     * @return
     */
    public Exception exceptionHandle(Exception e) {
        if (e instanceof CustomException) {
            return new CustomException(e.getMessage());
        } else if (e instanceof ProgramException) {
            return new ProgramException(e.getMessage());
        }
        return e;
    }

    /**
     * 获取请求属性封装为Map类型
     *
     * @param request
     * @return
     */
    protected HashMap<String, Object> getRequestMapSingle(HttpServletRequest request) {
        HashMap<String, Object> conditions = new HashMap<String, Object>();
        Map map = request.getParameterMap();
        for (Object o : map.keySet()) {
            String key = (String) o;
            conditions.put(key, ((String[]) map.get(key))[0]);
        }
        return conditions;
    }
}
