package com.grmkris.lightningloterry.exception;

public class RaffleAllreadyFinishedException extends Exception {
    public RaffleAllreadyFinishedException(String errorMessage){
        super(errorMessage);
    }
}