package com.grmkris.lightningloterry.model.database;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Raffle{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long raffleID;

    //poglej okoli @Version in hibernate
    private Timestamp startDate;
    private Timestamp endDate;
    private String winningNumbers;

    @OneToMany(mappedBy = "raffle")
    private Set<Tickets> tickets;

    @OneToMany(mappedBy="raffle")
    private Set<Winners> winner;
}