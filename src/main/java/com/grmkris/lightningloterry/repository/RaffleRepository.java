package com.grmkris.lightningloterry.repository;

import com.grmkris.lightningloterry.model.database.Raffle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long>{

    @Query(
    value = "SELECT * FROM raffle r ORDER BY r.startDate LIMIT 1",
    nativeQuery = true)
    Raffle findLatestRaffle();

}