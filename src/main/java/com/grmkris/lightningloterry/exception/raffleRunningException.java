package com.grmkris.lightningloterry.exception;

public class raffleRunningException extends Exception {
    //TODO add class of already running Raffle to this exception?
    public raffleRunningException(String errorMessage){
        super("Raffle is running!");
    }
}