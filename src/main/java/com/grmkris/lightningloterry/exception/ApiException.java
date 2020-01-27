package com.grmkris.lightningloterry.exception;

public class ApiException extends Exception {
    public ApiException(String errorMessage){
        super(errorMessage);
    }
}