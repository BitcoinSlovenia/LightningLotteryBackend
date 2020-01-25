package com.grmkris.lightningloterry.model.database;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Double amount;
    private Double fiatValue;
    private String lnPaymentRequest;
    private Long settledAt;

    @OneToMany(mappedBy="ticket")
    private Set<Winners> winner;
    
}