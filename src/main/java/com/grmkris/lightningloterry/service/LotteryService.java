package com.grmkris.lightningloterry.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.grmkris.lightningloterry.exception.RaffleNotFoundException;
import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.repository.RaffleRepository;
import com.grmkris.lightningloterry.repository.WinnersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LotteryService {

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private WinnersRepository winnersRepository;

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

    public List<Winners> findWinner(Long raffleID) {
        Optional<Raffle> raffleOpt = raffleRepository.findById(raffleID);
        if (raffleOpt.isPresent()){
            Raffle raffle = raffleOpt.get();
            if (raffle.getEndDate() == null){
                //raffle has not yet ended
                return null;
                //throw new RaffleStillRunningException(Raffle);
            }
            else{
                return winnersRepository.findByRaffle(raffle);
            }
        }
        else{
            return null;
            //throw new RaffleNotFoundException();
        }
    }

    private void generateRandomNumber(){

    }

    private void findAllEligibleTickets(){
        // morajo biti v časovnem obdobju -> od prejšnega zrebanja pa do -30minut do trenutnega zreba
        // status morajo imeti PAID -> to pomeni da so plačali lightning ticket
        //
    }

    private void stopCurrentRaffle(){
        Raffle currentRaffle = raffleRepository.findLatestRaffle();
        Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
        currentRaffle.setEndDate(ts);
        currentRaffle.setWinningNumbers("12345"); //replace with winningNumber
        raffleRepository.save(currentRaffle);
    }

}