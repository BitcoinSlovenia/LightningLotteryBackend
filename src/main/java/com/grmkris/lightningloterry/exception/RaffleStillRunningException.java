package com.grmkris.lightningloterry.exception;

public class RaffleStillRunningException extends Exception {
    public RaffleStillRunningException(String errorMessage){
        super(errorMessage);
    }
}