package com.grmkris.lightningloterry.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.grmkris.lightningloterry.exception.RaffleEndedException;
import com.grmkris.lightningloterry.exception.RaffleNotFoundException;
import com.grmkris.lightningloterry.exception.RaffleRunningException;
import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.RaffleStatus;
import com.grmkris.lightningloterry.repository.RaffleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaffleService{

    @Autowired
    private RaffleRepository raffleRepository;

    
    public Raffle newRaffle() throws RaffleRunningException {
        // TODO exception if raffleAlreadyRunning

        Raffle raffleCheck = raffleRepository.findRunningRaffle();
        if(raffleCheck == null ){
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            Raffle raffle = Raffle.builder().endDate(null).startDate(ts).tickets(null).status(RaffleStatus.RUNNING).build();
            raffleRepository.save(raffle);
            return raffle;
        }
        else {
            throw new RaffleRunningException("Raffle is already running");
        }
        
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

    public Raffle endRaffle(Long raffleID) throws RaffleNotFoundException, RaffleEndedException {
        Optional<Raffle> currentRaffleOpt = raffleRepository.findById(raffleID);
        if ( currentRaffleOpt.isPresent() ){
            Raffle currentRaffle = currentRaffleOpt.get();

            if(currentRaffle.getStatus() == RaffleStatus.COMPLETED || currentRaffle.getStatus() == RaffleStatus.STOPPED || currentRaffle.getStatus() == RaffleStatus.ENDED){
                throw new RaffleEndedException("Raffle already ended");
            }
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            currentRaffle.setEndDate(ts);
            currentRaffle.setWinningNumbers(generateRandomNumber()); 
            currentRaffle.setStatus(RaffleStatus.ENDED);
            raffleRepository.save(currentRaffle);
            return currentRaffle;
        }
        else{
            throw new RaffleNotFoundException("Raffle not found");
        }
        //TODO excpetion raffleAllreadyEndedException
        //TODO raffle doesn't exist
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