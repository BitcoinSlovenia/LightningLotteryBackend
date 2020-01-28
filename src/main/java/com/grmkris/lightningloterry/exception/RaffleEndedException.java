package com.grmkris.lightningloterry.exception;

public class RaffleEndedException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RaffleEndedException(String errorMessage) {
        super("Raffle ended!");
    }
}