package com.grmkris.lightningloterry.exception;

public class ApiException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ApiException(String errorMessage) {
        super(errorMessage);
    }
}