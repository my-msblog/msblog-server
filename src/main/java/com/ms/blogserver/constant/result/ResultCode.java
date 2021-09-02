package com.ms.blogserver.constant.result;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public enum ResultCode {
    /**
     *  404 未找到接口
     */
    NOT_FOUND(404),

    /**
     * 200 成功
     */
    SUCCESS(200),

    FAIL(400),

    FORBIDDEN(403),

    /**
     *  401未认证
     */
    UNAUTHORIZED(401),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
