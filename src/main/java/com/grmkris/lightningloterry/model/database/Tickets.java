package com.grmkris.lightningloterry.model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TicketID;

    @ManyToOne
    @JoinColumn(name="RAFFLE_ID")
    private Raffle RaffleID;
    
    private String openNodeID;
    private String customerName;
    private String customerEmail;
    private String customerDescription;
    private String numbers;
    private String status;
    private String amount;
    private Double fiatValue;
    private String lnPaymentRequest;
    
}