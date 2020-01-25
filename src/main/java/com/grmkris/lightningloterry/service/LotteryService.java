package com.grmkris.lightningloterry.service;

import java.sql.Timestamp;
import java.util.Date;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.repository.RaffleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scala.util.Random;

@Service
public class LotteryService{
    
    @Autowired
    private RaffleRepository raffleRepository;
    
    public Raffle newRaffle(){
        Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
        Raffle raffle = Raffle.builder().endDate(null).startDate(ts).tickets(null).build();
        raffleRepository.save(raffle);
        return raffle;
    }
    public void findWinner(){

    }

    /**
     * naključno generira 5 števil z random
     * <br>
     * https://www.scala-lang.org/api/current/scala/util/Random.html
     * @return String 5-ih števil 0-9
     */
    public String GenerateRandomNumber(){
        String finalStr = "";
        Random rnd = new Random();
        for(int i = 0; i < 5; i++){
            finalStr += rnd.nextInt(9);
        }
        return finalStr;
    }

    private void findAllEligibleTickets(){
        // morajo biti v časovnem obdobju -> od prejšnega zrebanja pa do -30minut do trenutnega zreba
        // status morajo imeti PAID -> to pomeni da so plačali lightning ticket
        //
    }

}