package com.grmkris.lightningloterry.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grmkris.lightningloterry.exception.RaffleNotFoundException;
import com.grmkris.lightningloterry.exception.RaffleStillRunningException;
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
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void newRaffleTest_Raffle() {
        Raffle raffle = raffleService.newRaffle();
        verify(raffleRepository, times(1)).save(raffle);
    }

    @Test
    public void stopRaffleTest_RaffleStopped() {
        // prepare
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        Raffle raffle = Raffle.builder().endDate(null).startDate(ts).tickets(null).build();
        raffleRepository.save(raffle);

        // when
        Raffle stoppedRaffle = raffleService.stopRaffle(raffle.getRaffleID());

        // test
        assertNotEquals(stoppedRaffle.getEndDate(), null);
        assertEquals(stoppedRaffle.getStartDate(), raffle.getStartDate());
        assertNotEquals(stoppedRaffle.getWinningNumbers(), null);
    }

    @Test
    public void whenNewRaffleTest_RaffleAlreadyRunningException() {
        Exception exception = assertThrows(RaffleStillRunningException.class, () -> {
            // prepare
            raffleService.newRaffle();
            // when
            Raffle raffle = raffleService.newRaffle();

        });

        String expectedMessage = "Raffle is already running!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void whenFinishRaffleTest_RaffleAlreadyRunningException() {
        Exception exception = assertThrows(RaffleStillRunningException.class, () -> {
            // prepare
            Raffle raffle = raffleService.newRaffle();
            Long raffleID = raffle.getRaffleID();
            raffle = raffleService.stopRaffle(raffleID);
            // when
            raffleService.stopRaffle(raffleID);
        });
        String expectedMessage = "Raffle has already finished!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenFinishRaffleTest_RaffleNotFoundException() {
        Exception exception = assertThrows(RaffleNotFoundException.class, () -> {
            // prepare
            Raffle raffle = raffleService.newRaffle();
            Long raffleID = raffle.getRaffleID();
            
            // when
            raffle = raffleService.stopRaffle(12323232L);
        });
        String expectedMessage = "Raffle not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenGetRaffles_RaffleList() {
        // prepare
        List<Raffle> raffleListExpected = new ArrayList<Raffle>();
        List<Raffle> raffleListActual = new ArrayList<Raffle>();

        Raffle raffle1 = raffleService.newRaffle();
        raffle1 = raffleService.stopRaffle(raffle1.getRaffleID());
        raffleListExpected.add(raffle1);

        Raffle raffle2 = raffleService.newRaffle();
        raffle2 = raffleService.stopRaffle(raffle2.getRaffleID());
        raffleListExpected.add(raffle2);

        Raffle raffle3 = raffleService.newRaffle();
        raffle3 = raffleService.stopRaffle(raffle3.getRaffleID());
        raffleListExpected.add(raffle3);

        raffleListActual = raffleService.getRaffles();

        assertEquals(raffleListExpected, raffleListActual);

        

    }

}