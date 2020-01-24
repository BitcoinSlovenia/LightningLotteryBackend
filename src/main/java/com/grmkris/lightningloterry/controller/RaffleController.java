package com.grmkris.lightningloterry.controller;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.service.LotteryService;
import com.grmkris.lightningloterry.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaffleController{

    @Autowired
    LotteryService lotteryService;

    @RequestMapping(path = "/raffle", method = RequestMethod.POST)
    public Raffle raffle() {
        Raffle raffle = lotteryService.newRaffle();
        return raffle;
    }

}