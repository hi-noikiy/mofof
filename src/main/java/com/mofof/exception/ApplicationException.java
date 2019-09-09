package com.mofof.exception;

/**
 * 业务异常
 * Created by hzh on 2018/10/1.
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}
