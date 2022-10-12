package com.huterox.whitehole.whiteholeuser.exception;

public class BadLoginTokenException extends Exception{
    public BadLoginTokenException(){}
    public BadLoginTokenException(String message){
        super(message);
    }
}
