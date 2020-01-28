package com.grmkris.lightningloterry.exception;

public class RaffleRunningException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // TODO add class of already running Raffle to this exception?
    public RaffleRunningException(String errorMessage){
        super("Raffle is running!");
    }
}