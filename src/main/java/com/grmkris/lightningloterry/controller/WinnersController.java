package com.grmkris.lightningloterry.controller;

import java.util.List;

import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.service.WinnersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WinnersController{

    @Autowired
    WinnersService winnersService;

    @RequestMapping(path = "/winners/{raffleID}", method = RequestMethod.GET)
    public List<Winners> winners(@PathVariable(value = "raffleID") Long raffleID) {
        List<Winners> winnersList = this.winnersService.getWinners(raffleID);
        return winnersList;
    }
    
}