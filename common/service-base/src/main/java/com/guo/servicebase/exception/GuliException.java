package com.guo.servicebase.exception;

public class GuliException extends RuntimeException{

    public GuliException(int code,String message) {
        super(message);
    }
}
