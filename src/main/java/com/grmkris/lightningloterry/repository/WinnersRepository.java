package com.grmkris.lightningloterry.repository;

import java.util.List;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.Winners;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnersRepository extends JpaRepository<Winners, Long>{
    
    List<Winners> findByRaffle(Raffle raffle);
}