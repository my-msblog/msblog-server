package com.ms.blogserver.exception;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultCode;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.constant.result.ResultString;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * @description:
 * @author: z
 * @time: 2021/5/14
 */
@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler implements ResponseBodyAdvice<Object> {


    /**
     * 捕获认证异常
     * @param e shiro异常
     * @param unauthorizedException 自定义认证异常
     * @return 返回code 401
     */
    @ExceptionHandler({ShiroException.class})
    @ResponseBody
    public Result handleShiroException(ShiroException e,UnauthorizedException unauthorizedException) {
        log.error("handleShiroException:shiro authorized error",e);
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, ResultString.NO_AUTHORIZED.DATA);
    }

    /**
     * 自定义CustomException异常捕获
     * @param e
     * @return 返回400
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result handleCustomException(CustomException e){
        log.error(e.getMessage(),e);
        return ResultFactory.buildFailResult(e.getMessage());
    }

    /**
     * 定义ProgramException程序异常捕获
     *
     * @param e
     * @return 返回500
     */
    @ExceptionHandler(ProgramException.class)
    @ResponseBody
    public Result handleProgramException(ProgramException e){
        log.error(e.getMessage(),e);
        return ResultFactory.buildResult(ResultCode.INTERNAL_SERVER_ERROR,ResultString.INTERNAL_ERROR.DATA,e.toString());
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public Result handleUnauthorizedException(UnauthorizedException unauthorizedException) {
        log.error(unauthorizedException.getMessage(),unauthorizedException);
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, ResultString.NO_AUTHORIZED.DATA);
    }
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result handleAuthorizationException(AuthorizationException e){
        //e.printStackTrace();
        log.error("handleAuthorizationException:"+LoginContexts.INSUFFICIENT_USER_PERMISSIONS,e);
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, LoginContexts.INSUFFICIENT_USER_PERMISSIONS);
    }

    /**
     * 其他异常
     * @param e
     * @return 返回500
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        log.error(e.getMessage(),e);
        if (e instanceof NoHandlerFoundException){
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,ResultString.PAGE_NO_FOUND.DATA);
        }
        return ResultFactory.buildResult(ResultCode.INTERNAL_SERVER_ERROR,ResultString.INTERNAL_ERROR.DATA,e.getMessage());
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (serverHttpRequest instanceof ServletServerHttpRequest && serverHttpResponse instanceof ServletServerHttpResponse) {
            System.out.println("请求路径:["+((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getRequestURI()+"]");
        }
        return o;
    }
}
