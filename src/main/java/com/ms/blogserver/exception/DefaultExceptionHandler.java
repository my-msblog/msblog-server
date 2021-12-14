package com.ms.blogserver.exception;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultCode;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.constant.result.ResultString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
     * @return 返回code 401
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ShiroException.class, AuthorizationException.class})
    @ResponseBody
    public Result handleShiroException(ShiroException e, AuthorizationException authorizationException) {
        log.error("handleShiroException:shiro authorized error",e);
        log.error("handleShiroException:AuthorizationException ", authorizationException);
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, ResultString.NO_AUTHORIZED);
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
        return ResultFactory.buildResult(ResultCode.INTERNAL_SERVER_ERROR,ResultString.INTERNAL_ERROR,e.toString());
    }

    @ExceptionHandler({CustomAuthorizedException.class})
    @ResponseBody
    public Result handleUnauthorizedException(CustomAuthorizedException customAuthorizedException) {
        log.error(customAuthorizedException.getMessage(), customAuthorizedException);
        if(StringUtils.isEmpty(customAuthorizedException.getMessage())){
            return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, ResultString.NO_AUTHORIZED);
        }
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, customAuthorizedException.getMessage());
    }

    /**
     * 其他异常
     * @param e
     * @return 返回500
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        log.error(e.getMessage(),e);
        if (e instanceof NoHandlerFoundException){
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,ResultString.PAGE_NO_FOUND);
        }
        return ResultFactory.buildResult(ResultCode.INTERNAL_SERVER_ERROR,ResultString.INTERNAL_ERROR,e.getMessage());
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o;
    }
}
