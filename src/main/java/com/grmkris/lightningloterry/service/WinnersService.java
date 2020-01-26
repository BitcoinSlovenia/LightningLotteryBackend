package com.grmkris.lightningloterry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.RaffleStatus;
import com.grmkris.lightningloterry.model.database.Tickets;
import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.repository.RaffleRepository;
import com.grmkris.lightningloterry.repository.TicketRepository;
import com.grmkris.lightningloterry.repository.WinnersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnersService {

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private WinnersRepository winnersRepository;

    @Autowired 
    private TicketRepository ticketRespository;

    public List<Winners> getWinners(Long raffleID) {
        Optional<Raffle> raffleOpt = raffleRepository.findById(raffleID);
        if (raffleOpt.isPresent()){
            Raffle raffle = raffleOpt.get();
            if(raffle.getStatus().equals(RaffleStatus.COMPLETED)){

            }
            if (raffle.getEndDate() == null){
                //raffle has not yet ended
                return null;
                //throw new RaffleStillRunningException(Raffle);
            }
            else{
                return winnersRepository.findByRaffle(raffle);
            }
        }
        else{
            return null;
            //throw new RaffleNotFoundException();
        }
    }

    public List<Winners> findWinner() {
        Raffle raffle = raffleRepository.findCompletedRaffle();
        List<Winners> winnersList = new ArrayList<Winners>();
        if (raffle == null){
            // TODO
            // return RaffleNotFoundException 
            return null;
        }
        else{
            List<Tickets> ticketList = ticketRespository.findByRaffle(raffle);
            Tickets ticket;
            for(int i = 0; i < ticketList.size(); i++){
                ticket = ticketList.get(i);
                if (ticket.getNumbers().equals(raffle.getWinningNumbers())){
                    Winners winner = Winners.builder()
                        .prizeType("5")
                        .prizeWon(Double.parseDouble(String.valueOf(ticketList.size()*180)))
                        .raffle(raffle)
                        .status("PENDING")
                        .ticket(ticket)
                        .build();
                    winnersRepository.save(winner);
                    winnersList.add(winner);
                }
            }

        }

        return null;
    }

}