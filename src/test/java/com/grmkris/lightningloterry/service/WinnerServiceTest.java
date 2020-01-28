package com.grmkris.lightningloterry.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.RaffleStatus;
import com.grmkris.lightningloterry.model.database.Tickets;
import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.repository.RaffleRepository;
import com.grmkris.lightningloterry.repository.TicketRepository;
import com.grmkris.lightningloterry.repository.WinnersRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class WinnerServiceTest {

    @InjectMocks
    WinnersService winnerService;

    @Mock
    WinnersRepository winnersRepository;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    RaffleRepository raffleRepository;

    public Long RAFFLEID;

    //@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        // prepare database
            // start new raffle
        Date dateStart= new Date();
        long timeStart = dateStart.getTime();
        Timestamp tsStart = new Timestamp(timeStart);
        Raffle raffle = this.raffleRepository.save(Raffle.builder().startDate(tsStart).status(RaffleStatus.RUNNING).build());
        //set RAFFLE_ID to public variable for later usage in tests
        this.RAFFLEID = raffle.getRaffleID();
            //entry 3 tickets
            // 2 winning tickets
        this.ticketRepository.save(Tickets.builder()
            .amount(200.00)
            .customerDescription("customerDescription")
            .customerEmail("customerEmail1")
            .customerName("customerName1")
            .fiatValue(1.22)
            .lnPaymentRequest("lnPaymentRequest1")
            .numbers("12345")
            .openNodeID("123131")
            .raffle(raffle)
            .build());
            
        this.ticketRepository.save(Tickets.builder()
            .amount(200.00)
            .customerDescription("customerDescription")
            .customerEmail("customerEmail2")
            .customerName("customerName2")
            .fiatValue(1.22)
            .lnPaymentRequest("lnPaymentRequest2")
            .numbers("12345")
            .openNodeID("1231312")
            .raffle(raffle)
            .build());
        
        this.ticketRepository.save(Tickets.builder()
            .amount(200.00)
            .customerDescription("customerDescription3")
            .customerEmail("customerEmail3")
            .customerName("customerName3")
            .fiatValue(1.22)
            .lnPaymentRequest("lnPaymentRequest3")
            .numbers("54321")
            .openNodeID("1231313")
            .raffle(raffle)
            .build());
        
            //stop raffle
        Date dateEnd = new Date();
        long timeEnd = dateEnd.getTime();
        Timestamp tsEnd = new Timestamp(timeEnd);
        raffle.setEndDate(tsEnd);
        raffle.setWinningNumbers("12345");
        this.raffleRepository.save(raffle);

    }

    //@Test
    public void findPastWinnerStatusFinishedTest_winnerListStatusCompleted() {

    }

}