package com.grmkris.lightningloterry.model.database;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class WinnersID implements Serializable {
    private Long raffleID;
    private Long ticketID;
}