package com.grmkris.lightningloterry.exception;

public class RaffleNotFoundException extends Exception {
    public RaffleNotFoundException(String errorMessage){
        super("Raffle not found!");
    }
}