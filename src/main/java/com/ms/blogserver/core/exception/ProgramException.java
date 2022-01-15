package com.ms.blogserver.core.exception;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public class ProgramException extends RuntimeException{
    /**
     * 带error信息
     *
     * @param msg
     */
    public ProgramException(String msg) {
        super(msg);
    }
    public ProgramException(String msg, Throwable cause){
        super(msg,cause);
    }
    public ProgramException(Exception e){
        super(e);
    }

    public ProgramException() {
        super();
    }
}
