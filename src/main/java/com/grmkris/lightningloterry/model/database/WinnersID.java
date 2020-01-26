package com.grmkris.lightningloterry.model.database;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class WinnersID implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long raffleID;
    private Long ticketID;
}