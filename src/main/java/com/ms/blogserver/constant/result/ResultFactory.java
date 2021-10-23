package com.ms.blogserver.constant.result;
/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */

public class ResultFactory {

    public static Result<String> buildSuccessResult(){
        return buildSuccessResult("");
    }

    public static<T> Result<T> buildSuccessResult(T data) {
        return buildResult(ResultCode.SUCCESS, "success", data);
    }

    public static Result<String> buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, "");
    }

    public static<T> Result<T> buildResult(ResultCode resultCode, String message, T data) {
        return buildResult(resultCode.code, message, data);
    }

    public static<T> Result<T> buildResult(ResultCode resultCode, ResultString resultString, T data){
        return buildResult(resultCode.code,resultString.data,data);
    }

    public static Result<String> buildResult(ResultCode resultCode, ResultString resultString){
        return  buildResult(resultCode,resultString.data);
    }

    public static Result<String> buildResult(ResultCode resultCode, String message){
        return buildResult(resultCode,message,"");
    }

    public static<T> Result<T> buildResult(int resultCode, String message, T data) {
        return new Result<>(resultCode, message, data);
    }
}
