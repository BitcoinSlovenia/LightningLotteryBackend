package com.grmkris.lightningloterry.model.database;

import java.util.Set;

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
@Table(name = "Tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketID;

    @ManyToOne
    @JoinColumn(name="raffleID", nullable=false)
    private Raffle raffle;
    
    private String openNodeID;
    private String customerName;
    private String customerEmail;
    private String customerDescription;
    private String numbers;
    private String status;
    private String amount;
    private Double fiatValue;
    private String lnPaymentRequest;

    @OneToMany(mappedBy="ticket")
    private Set<Winners> winner;
    
}