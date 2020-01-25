package com.grmkris.lightningloterry.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.grmkris.lightningloterry.model.TicketRequest;
import com.grmkris.lightningloterry.model.TicketResponse;
import com.grmkris.lightningloterry.model.database.Tickets;
import com.grmkris.lightningloterry.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public TicketResponse ticket(@RequestParam Long ticketId, @RequestParam Boolean refresh) {
        if(refresh == false){
            Tickets ticket =  ticketservice.getTicket(ticketId);
            return TicketResponse.builder()
                .amount(ticket.getAmount())
                .customerEmail(ticket.getCustomerEmail())
                .customerName(ticket.getCustomerName())
                .fiatValue(ticket.getFiatValue())
                .lightningInvoice(ticket.getLnPaymentRequest())
                .settledAt(ticket.getSettledAt())
                .numbers(ticket.getNumbers())
                .openNodeID(ticket.getOpenNodeID())
                .status(ticket.getStatus())
                .ticketID(ticket.getTicketID())
                .build();
        }
        else{
            return ticketservice.getTicketOpenNode(ticketId);
        }

    }

    @RequestMapping(path = "/tickets", method = RequestMethod.GET)
    public List<TicketResponse> tickets() {
        List<TicketResponse> ticketResponseList = new ArrayList<TicketResponse>();
        List<Tickets> ticketList = ticketservice.getAllTickets();
        Tickets ticket = null;
        Iterator<Tickets> it = ticketList.iterator();
        while(it.hasNext()){
            ticket = it.next();
            ticketResponseList.add(TicketResponse.builder()
                .amount(ticket.getAmount())
                .customerEmail(ticket.getCustomerEmail())
                .customerName(ticket.getCustomerName())
                .fiatValue(ticket.getFiatValue())
                .lightningInvoice(ticket.getLnPaymentRequest())
                .settledAt(ticket.getSettledAt())
                .numbers(ticket.getNumbers())
                .openNodeID(ticket.getOpenNodeID())
                .status(ticket.getStatus())
                .ticketID(ticket.getTicketID())
                .build()
            );
        }
        return ticketResponseList;
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