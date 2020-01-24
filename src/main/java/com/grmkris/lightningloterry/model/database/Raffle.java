package com.grmkris.lightningloterry.model.database;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "Raffle")
public class Raffle{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long raffleID;

    private Timestamp startDate;
    private Timestamp endDate;
    private String winningNumbers;

    @OneToMany(mappedBy = "raffle")
    private Set<Tickets> tickets;

    @OneToMany(mappedBy="raffle")
    private Set<Winners> winner;
}