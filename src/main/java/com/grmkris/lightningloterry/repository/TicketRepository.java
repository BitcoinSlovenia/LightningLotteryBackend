package com.grmkris.lightningloterry.repository;

import java.util.List;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.Tickets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long>{

    List<Tickets> findByRaffle(Raffle raffle);

    List<Tickets> findByWinningNumbers(String number);
}