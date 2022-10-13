package com.huterox.whitehole.whiteholeuser.exception;

public class NotLoginException extends RuntimeException{
    public NotLoginException(){}
    public NotLoginException(String message){
        super(message);
    }
}
