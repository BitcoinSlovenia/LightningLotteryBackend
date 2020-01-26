package com.grmkris.lightningloterry.model.database;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "Winners")
@Builder
public class Winners {

    @EmbeddedId
    WinnersID winnersID;

    @ManyToOne @MapsId("raffleID")
    @JoinColumn(name="raffleID", nullable=false)
    private Raffle raffle;

    @ManyToOne @MapsId("ticketID")
    @JoinColumn(name="ticketID", nullable=false)
    private Tickets ticket;

    private Double prizeWon;
    private String prizeType;
    private String status;
}
