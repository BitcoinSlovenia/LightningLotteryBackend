package com.grmkris.lightningloterry.controller;

import com.grmkris.lightningloterry.model.TicketRequest;
import com.grmkris.lightningloterry.model.TicketResponse;
import com.grmkris.lightningloterry.service.TicketService;

import org.brunocvcunha.opennode.api.model.OpenNodeCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController{

    @Autowired
    TicketService ticketservice;

    @RequestMapping(path = "/ticket", method = RequestMethod.POST)
    public TicketResponse ticket(@RequestBody TicketRequest ticketRequest) {
        return ticketservice.newTicket(ticketRequest);
    }

    @RequestMapping(path = "/ticket", method = RequestMethod.GET)
    public OpenNodeCharge ticket(@RequestParam String ticketId) {
        return ticketservice.getTicket(ticketId);
        // TODO popravi da vrneš ticketResponse, povezi z bazo in shranjuj podatke
    }

    @RequestMapping(path = "/tickets", method = RequestMethod.GET)
    public TicketResponse[] tickets() {
        TicketResponse[] ticketResponseArray = null;
        return ticketResponseArray;
    }



    //POST /ticket

    //GET tickets

    // tickets/{id}

    /*
    POST /ticket
    Request:
        String[] numbers
    Response: 
        ticketID;
        LIGHTNING_INVOICE, (za tole ti bom dal navodila kako prikazat qr)

GET /tickets
    Response: Returns all tickets with status PAID

GET tickets/{id} //če ma ticket status waiting, in boš v request dodal lightning invoice, bom sprozil nakazilo in v tabeli označil "PAID"
    Request:
        lightningInvoice(optional)
    Response: 
        ticketID, ticketName, roundID, numbers...
        */

}