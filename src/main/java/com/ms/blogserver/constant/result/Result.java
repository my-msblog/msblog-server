package com.ms.blogserver.constant.result;

import lombok.Data;
import lombok.ToString;

/**
 * @description: 接口返回类型封装
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
public class Result<T> {

    private int code;
    private String msg;
    private T data ;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
