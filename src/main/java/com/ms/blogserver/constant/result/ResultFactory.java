package com.ms.blogserver.constant.result;

public class ResultFactory {

    public static Result buildSuccessResult(){
        return buildSuccessResult("");
    }

    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "success", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.CODE, message, data);
    }
    public static Result buildResult(ResultCode resultCode, String message){
        return buildResult(resultCode,message,null);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
