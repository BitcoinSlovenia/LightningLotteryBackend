package com.grmkris.lightningloterry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.RaffleStatus;
import com.grmkris.lightningloterry.model.database.Tickets;
import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.model.database.WinnersStatus;
import com.grmkris.lightningloterry.repository.RaffleRepository;
import com.grmkris.lightningloterry.repository.TicketRepository;
import com.grmkris.lightningloterry.repository.WinnersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WinnersService {

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private WinnersRepository winnersRepository;

    @Autowired
    private TicketRepository ticketRespository;

    public List<Winners> getWinners(Long raffleID) {
        Optional<Raffle> raffleOpt = raffleRepository.findById(raffleID);
        List<Winners> winnersList = new ArrayList<Winners>();
        if (raffleOpt.isPresent()) {
            Raffle raffle = raffleOpt.get();
            if (raffle.getStatus().equals(RaffleStatus.COMPLETED)) { // completed winners allready in database
                winnersList = this.winnersRepository.findByRaffle(raffle);
            }
            if (raffle.getStatus().equals(RaffleStatus.FINISHED)) { // winners not yet in database, find them by hand
                winnersList = this.findWinner(raffle);
                raffle.setStatus(RaffleStatus.COMPLETED);
                raffleRepository.save(raffle);
            }
            if (raffle.getStatus().equals(RaffleStatus.RUNNING)) {
                // raffle has not yet ended throw new RaffleStillRunningException(Raffle);
                return null;
            } 
            if (raffle.getStatus().equals(RaffleStatus.STOPPED)) {
                // raffle has been stopped for some reason // throw new RaffleStoppedSException(Raffle);
                return null;
            } 
        } else {
            return null;
            // throw new RaffleNotFoundException();
        }
        return winnersList;
    }

    public List<Winners> findWinner(Raffle raffle) {
        List<Tickets> ticketList = ticketRespository.findByRaffle(raffle);
        List<Winners> winnersList = new ArrayList<Winners>();
        Tickets ticket;
        for (int i = 0; i < ticketList.size(); i++) {
            ticket = ticketList.get(i);
            if (ticket.getNumbers().equals(raffle.getWinningNumbers())) {
                Winners winner = Winners.builder().prizeType("5")
                        .prizeWon(Double.parseDouble(String.valueOf(ticketList.size() * 180))).raffle(raffle)
                        .status(WinnersStatus.PENDING).ticket(ticket).build();
                winnersRepository.save(winner);
                winnersList.add(winner);
            }
        }
        return winnersList;
    }

}