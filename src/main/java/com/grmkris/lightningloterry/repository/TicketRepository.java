package com.grmkris.lightningloterry.repository;

import com.grmkris.lightningloterry.model.database.Tickets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long>{

}