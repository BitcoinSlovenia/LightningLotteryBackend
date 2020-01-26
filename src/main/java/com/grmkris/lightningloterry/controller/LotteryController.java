package com.grmkris.lightningloterry.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.service.LotteryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController{

    @Autowired
    LotteryService lotteryService;

    @RequestMapping(path = "/lottery/{raffleID}", method = RequestMethod.GET)
    public List<Winners> lottery(@PathVariable(value = "raffleID") Long raffleID) {
        List<Winners> winnersList = this.lotteryService.findPastWinner(raffleID);
        return winnersList;
    }

    @RequestMapping(path = "/lottery", method = RequestMethod.POST)
    public List<Winners> lotteryWinners() {
        List<Winners> winnersList = this.lotteryService.findWinner();
        return winnersList;
    }

}