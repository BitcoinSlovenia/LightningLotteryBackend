package com.grmkris.lightningloterry.exception;

public class RaffleEndedException extends Exception {
    public RaffleEndedException(String errorMessage){
        super("Raffle ended!");
    }
}