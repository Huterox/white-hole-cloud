package com.huterox.whitehole.whiteholeuser.exception;

public class BadLoginTokenException extends RuntimeException{
    public BadLoginTokenException(){}
    public BadLoginTokenException(String message){
        super(message);
    }
}
