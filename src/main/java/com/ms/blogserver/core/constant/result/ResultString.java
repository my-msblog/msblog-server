package com.ms.blogserver.core.constant.result;

/**
 * @description:
 * @author: z
 * @time: 2021/5/14
 */
public enum ResultString {

    /**
     * 404
     */
    PAGE_NO_FOUND("接口未找到"),

    /**
     * 401
     */
    NO_AUTHORIZED("认证错误：未完成认证"),

    /**
     * 500
     */
    INTERNAL_ERROR("服务器内部错误"),

    /**
     * 400
     */
    BAD_REQUEST("错误请求"),

    /**
     * 403
     */
    FORBIDDEN("禁止访问"),
    ;
    public String data;

    ResultString(String date) {
        this.data = date;
    }
}
