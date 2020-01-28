package com.grmkris.lightningloterry.exception;

public class RaffleNotFoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RaffleNotFoundException(String errorMessage) {
        super("Raffle not found!");
    }
}