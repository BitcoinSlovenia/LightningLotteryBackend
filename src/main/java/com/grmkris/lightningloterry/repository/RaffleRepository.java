package com.grmkris.lightningloterry.repository;

import com.grmkris.lightningloterry.model.database.Raffle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long>{

    @Query(
        value = "SELECT * FROM raffle r ORDER BY r.start_date DESC LIMIT 1",
        nativeQuery = true)
    Raffle findRunningRaffle();

    @Query(
        value = "SELECT * FROM raffle r WHERE r.start_date IS NOT NULL AND r.end_date IS NOT NULL and r.winning_numbers IS NOT NULL ORDER BY r.start_date DESC LIMIT 1",
        nativeQuery = true)
    Raffle findCompletedRaffle();
    

}