package com.grmkris.lightningloterry.model.database;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
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
    private Integer[] numbers;
    private String status;
    private Double amount;
    private Double fiatValue;
    private String lnPaymentRequest;
    private Long settledAt;

    @OneToMany(mappedBy="ticket")
    private Set<Winners> winner;
    
}