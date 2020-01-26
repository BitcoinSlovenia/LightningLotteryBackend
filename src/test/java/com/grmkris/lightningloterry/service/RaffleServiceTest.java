package com.grmkris.lightningloterry.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.util.Date;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.repository.RaffleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RaffleServiceTest {
 
    @InjectMocks
    RaffleService raffleService;
 
    @Mock
    RaffleRepository raffleRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void newRaffleTest_Raffle() {
        Raffle raffle = raffleService.newRaffle();
        verify(raffleRepository, times(1)).save(raffle);
    }

    @Test
    public void stopRaffleTest_RaffleStopped(){
        //prepare
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        Raffle raffle = Raffle.builder().endDate(null).startDate(ts).tickets(null).build();
        raffleRepository.save(raffle);

        //when
        Raffle stoppedRaffle = raffleService.stopRaffle(raffle.getRaffleID());

        //test
        assertNotEquals(stoppedRaffle.getEndDate(),null);
        assertEquals(stoppedRaffle.getStartDate(), raffle.getStartDate());
        assertNotEquals(stoppedRaffle.getWinningNumbers(), null);
    }
 
}