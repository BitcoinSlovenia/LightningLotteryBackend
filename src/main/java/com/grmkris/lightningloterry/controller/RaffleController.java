package com.grmkris.lightningloterry.controller;

import java.util.List;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.service.LotteryService;
import com.grmkris.lightningloterry.service.RaffleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaffleController{

    @Autowired
    RaffleService raffleService;

    @RequestMapping(path = "/raffles", method = RequestMethod.POST)
    public Raffle raffle() {
        Raffle raffle = raffleService.newRaffle();
        return raffle;
    }

    @RequestMapping(path = "/raffles/{raffleID}", method = RequestMethod.GET)
    public Raffle getRaffle(@PathVariable(value = "raffleID") Long raffleID) {
        Raffle raffle = raffleService.getRaffle(raffleID);
        return raffle;
    }
    
    /**
     * 
     * @return String 5-ih števil 0-9, ki ga generira funkcija GenerateRandomNumber
     */
    @RequestMapping(path = "/random", method = RequestMethod.POST)
    public String random() {
        return lotteryService.GenerateRandomNumber();
    }

    @RequestMapping(path = "/raffles", method = RequestMethod.GET)
    public List<Raffle> getRaffles(@PathVariable(value = "raffleID") Long raffleID) {
        List<Raffle> raffleList = raffleService.getRaffles();
        return raffleList;
    }



}