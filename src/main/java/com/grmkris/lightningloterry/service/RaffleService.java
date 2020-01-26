package com.grmkris.lightningloterry.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public Raffle stopRaffle(Long raffleID){
        Optional<Raffle> currentRaffleOpt = raffleRepository.findById(raffleID);
        if ( currentRaffleOpt.isPresent() ){
            Raffle currentRaffle = currentRaffleOpt.get();
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            currentRaffle.setEndDate(ts);
            currentRaffle.setWinningNumbers(generateRandomNumber()); 
            raffleRepository.save(currentRaffle);
            return currentRaffle;
        }
        return null;
        //TODO excpetion raffleAllreadyEndedException
    }

    /**
     * naključno generira 5 števil z random
     * <br>
     * @return String 5-ih števil 0-9
     */
    private String generateRandomNumber(){
        String finalStr = "";
        Random rnd = new Random();
        for(int i = 0; i < 5; i++){
            finalStr += rnd.nextInt(9);
        }
        return finalStr;
    }

}