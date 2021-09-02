package com.ms.blogserver.constant.result;
/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */

public class ResultFactory {

    public static Result buildSuccessResult(){
        return buildSuccessResult("");
    }

    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "success", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, "");
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }
    public static Result buildResult(ResultCode resultCode, String message){
        return buildResult(resultCode,message,"");
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
