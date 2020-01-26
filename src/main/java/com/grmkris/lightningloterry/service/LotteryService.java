package com.grmkris.lightningloterry.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.grmkris.lightningloterry.exception.RaffleNotFoundException;
import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.Tickets;
import com.grmkris.lightningloterry.model.database.Winners;
import com.grmkris.lightningloterry.repository.RaffleRepository;
import com.grmkris.lightningloterry.repository.TicketRepository;
import com.grmkris.lightningloterry.repository.WinnersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scala.util.Random;

@Service
public class LotteryService {

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private WinnersRepository winnersRepository;

    @Autowired 
    private TicketRepository ticketRespository;

    public List<Winners> findPastWinner(Long raffleID) {
        Optional<Raffle> raffleOpt = raffleRepository.findById(raffleID);
        if (raffleOpt.isPresent()){
            Raffle raffle = raffleOpt.get();
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

    /**
     * naključno generira 5 števil z random
     * <br>
     * https://www.scala-lang.org/api/current/scala/util/Random.html
     * @return String 5-ih števil 0-9
     */
    public String GenerateRandomNumber(){
        String finalStr = "";
        Random rnd = new Random();
        for(int i = 0; i < 5; i++){
            finalStr += rnd.nextInt(9);
        }
        return finalStr;
    }

    private void findAllEligibleTickets(){
        // morajo biti v časovnem obdobju -> od prejšnega zrebanja pa do -30minut do trenutnega zreba
        // status morajo imeti PAID -> to pomeni da so plačali lightning ticket
        //
    }



}