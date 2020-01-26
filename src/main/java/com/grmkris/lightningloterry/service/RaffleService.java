package com.grmkris.lightningloterry.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

import com.grmkris.lightningloterry.exception.RaffleNotFoundException;
import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.repository.RaffleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaffleService{

    @Autowired
    private RaffleRepository raffleRepository;

    
    public Raffle newRaffle() {
        // First we stop currently running raffle
        stopCurrentRaffle();
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        Raffle raffle = Raffle.builder().endDate(null).startDate(ts).tickets(null).build();
        raffleRepository.save(raffle);
        return raffle;
    }

    public Raffle getRaffle(Long raffleID){
        Optional<Raffle> raffleOpt = raffleRepository.findById(raffleID);
        if(raffleOpt.isPresent()){
            return raffleOpt.get();
        }else{
            //TODO
            return null;
            //throw new RaffleNotFoundException();
        }
    }

    public List<Raffle> getRaffles(){
        return raffleRepository.findAll();
    }

    private void stopCurrentRaffle(){
        Raffle currentRaffle = raffleRepository.findRunningRaffle();
        if ( currentRaffle != null ){
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            currentRaffle.setEndDate(ts);
            currentRaffle.setWinningNumbers("12345"); //replace with winningNumber
            raffleRepository.save(currentRaffle);
        }
    }
}