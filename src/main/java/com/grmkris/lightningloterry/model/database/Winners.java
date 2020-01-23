package com.grmkris.lightningloterry.model.database;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "winners")
public class Winners {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "raffleID", referencedColumnName = "raffleID")
    private Raffle raffle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticketID", referencedColumnName = "ticketID")
    private Tickets ticket;

    private Double prizeWon;
    private String prizeType;
    private String status;
}