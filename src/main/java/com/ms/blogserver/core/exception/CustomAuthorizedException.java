package com.ms.blogserver.core.exception;

/**
 * @description: 自定义认证异常
 * @author: zhh
 * @time: 2021/5/21
 */
public class CustomAuthorizedException extends RuntimeException{
    public CustomAuthorizedException(String msg) {
        super(msg);
    }

    public CustomAuthorizedException() {
        super();
    }
}
