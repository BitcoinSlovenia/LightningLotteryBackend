package com.grmkris.lightningloterry.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.grmkris.lightningloterry.exception.RaffleEndedException;
import com.grmkris.lightningloterry.exception.RaffleNotFoundException;
import com.grmkris.lightningloterry.exception.RaffleRunningException;
import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.RaffleStatus;
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
    public void getRaffles_RaffleList() {
        // prepare
        List<Raffle> raffleListExpected = new ArrayList<Raffle>();
        List<Raffle> raffleListActual = new ArrayList<Raffle>();
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        raffleListExpected
                .add(Raffle.builder().endDate(ts).startDate(ts).status(RaffleStatus.COMPLETED).raffleID(1L).build());
        raffleListExpected
                .add(Raffle.builder().endDate(ts).startDate(ts).status(RaffleStatus.COMPLETED).raffleID(2L).build());
        raffleListExpected
                .add(Raffle.builder().endDate(ts).startDate(ts).status(RaffleStatus.COMPLETED).raffleID(3L).build());

        when(raffleRepository.findAll()).thenReturn(raffleListExpected);

        // test
        raffleListActual = raffleService.getRaffles();

        assertEquals(raffleListExpected.size(), raffleListActual.size());
        verify(raffleRepository, times(1)).findAll();
    }

    @Test
    public void newRaffleTest_Raffle() throws RaffleRunningException {

        Raffle raffle = raffleService.newRaffle();
        verify(raffleRepository, times(1)).save(raffle);
    }

    @Test
    public void getRaffleByIdTest_Raffle() {
        // prepare
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        Raffle raffleExpected = Raffle.builder().endDate(ts).startDate(ts).status(RaffleStatus.RUNNING).raffleID(1L)
                .build();
        when(raffleRepository.findById(1L)).thenReturn(Optional.of(raffleExpected));

        Raffle raffleActual = raffleService.getRaffle(1L);

        assertEquals(raffleActual.getStartDate(), ts);
        assertEquals(raffleActual.getEndDate(), ts);
        assertEquals(raffleActual.getStatus(), RaffleStatus.RUNNING);

    }

    @Test
    public void endRaffleTest_RaffleEnded() throws RaffleNotFoundException, RaffleEndedException {
        // prepare
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        Raffle raffleExpected = Raffle.builder().startDate(ts).raffleID(1L).status(RaffleStatus.RUNNING).build();


        when(raffleRepository.findById(1L)).thenReturn(Optional.of(raffleExpected));
        // when

        Raffle endedRaffle = raffleService.endRaffle(raffleExpected.getRaffleID());

        // test
        assertNotEquals(endedRaffle.getEndDate(), null);
        assertEquals(endedRaffle.getStartDate(), ts);
        assertEquals(endedRaffle.getStatus(), RaffleStatus.ENDED);

    }

    @Test
    public void whenNewRaffleTest_RaffleRunningException() {
        Exception exception = assertThrows(RaffleRunningException.class, () -> {
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            Raffle raffleExpected = Raffle.builder().startDate(ts).raffleID(1L).status(RaffleStatus.RUNNING).build();

            when(raffleRepository.findRunningRaffle()).thenReturn(raffleExpected);

            raffleService.newRaffle();
        });

        String expectedMessage = "Raffle is running!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void whenEndRaffleTest_RaffleEndedException() {
        Exception exception = assertThrows(RaffleEndedException.class, () -> {
            // prepare
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            Raffle raffleExpected = Raffle.builder().startDate(ts).raffleID(1L).status(RaffleStatus.ENDED).build();

            when(raffleRepository.findById(1L)).thenReturn(Optional.of(raffleExpected));

            raffleService.endRaffle(1L);

        });
        String expectedMessage = "Raffle ended!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenEndRaffleTest_RaffleNotFoundException() {
        Exception exception = assertThrows(RaffleNotFoundException.class, () -> {
            raffleService.endRaffle(1L);
        });
        String expectedMessage = "Raffle not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}