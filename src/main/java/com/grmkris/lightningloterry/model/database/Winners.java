package com.grmkris.lightningloterry.model.database;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Winners")
public class Winners {

    @EmbeddedId private WinnersID id;

    @ManyToOne @MapsId("raffleID")
    private Raffle raffle;

    @OneToOne @MapsId("ticketID")
    private Tickets ticket;

    private Double prizeWon;
    private String prizeType;
    private String status;
}
