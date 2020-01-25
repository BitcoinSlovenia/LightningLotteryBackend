package com.grmkris.lightningloterry.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.grmkris.lightningloterry.model.TicketRequest;
import com.grmkris.lightningloterry.model.TicketResponse;
import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.model.database.Tickets;
import com.grmkris.lightningloterry.repository.RaffleRepository;
import com.grmkris.lightningloterry.repository.TicketRepository;

import org.brunocvcunha.opennode.api.OpenNodeService;
import org.brunocvcunha.opennode.api.OpenNodeServiceFactory;
import org.brunocvcunha.opennode.api.model.OpenNodeCharge;
import org.brunocvcunha.opennode.api.model.OpenNodeCreateCharge;
import org.brunocvcunha.opennode.api.model.OpenNodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketService {

    private static Double TICKET_AMOUNT = 200.00;

    OpenNodeService service = OpenNodeServiceFactory.buildClient("b95d29ac-4ce9-45c9-ab9e-8767b35a01de");

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired RaffleRepository raffleRepository;

    public TicketResponse newTicket(TicketRequest ticketRequest) {

        try {

            if(ticketRequest.getCustomerEmail().equals("")){
                ticketRequest.setCustomerEmail("kristjan.grm1@gmail.com");
            }
            if(ticketRequest.getCustomerName().equals("")){
                ticketRequest.setCustomerName("Anonymous");
            }
            if(ticketRequest.getDescription().equals("Anonymous")){
                ticketRequest.setDescription("Anonymous");
            }
            // TODO: shrani numbers v bazo oziroma celoten ticketRequest
            OpenNodeCreateCharge createCharge = OpenNodeCreateCharge.builder().orderId("internal id")
                    .description(ticketRequest.getDescription()).amount(TICKET_AMOUNT)
                    .callbackUrl(ticketRequest.getCallbackUrl()).customerEmail(ticketRequest.getCustomerEmail())
                    .customerName(ticketRequest.getCustomerName())
                    // .currency(OpenNodeCurrency.EUR) // default is satoshis
                    .build();

            OpenNodeCharge createdCharge = service.createCharge(createCharge).execute().body().getData();

            TicketResponse ticketResponse = TicketResponse.builder().amount(TICKET_AMOUNT)
                    .customerEmail(ticketRequest.getCustomerEmail()).customerName(createdCharge.getName())
                    .customerDescription(ticketRequest.getDescription())
                    .fiatValue(createdCharge.getFiatValue()).lightningInvoice(createdCharge.getLightningInvoice().getPayreq())
                    .numbers(ticketRequest.getNumbers()).openNodeID(createdCharge.getId())
                    .settledAt(createdCharge.getLightningInvoice().getSettledAt())
                    .status(createdCharge.getStatus().name()).build();

            // Insert v bazo
            Raffle raffle = raffleRepository.findLatestRaffle();

            Tickets ticket = Tickets.builder()
                .amount(ticketResponse.getAmount())
                .customerDescription(createCharge.getDescription())
                .customerEmail(ticketResponse.getCustomerEmail())
                .customerName(ticketResponse.getCustomerName())
                .fiatValue(ticketResponse.getFiatValue())
                .lnPaymentRequest(ticketResponse.getLightningInvoice())
                .numbers(ticketResponse.getNumbers())
                .openNodeID(ticketResponse.getOpenNodeID())
                .raffle(raffle)
                .settledAt(ticketResponse.getSettledAt())
                .status(ticketResponse.getStatus())
                .ticketID(ticketResponse.getTicketID())
                .build();

                ticket = ticketRepository.save(ticket);

                ticketResponse.setTicketID(ticket.getTicketID());
            return ticketResponse;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            // API excpetion poglej proper exception handling
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;

    }

    public List<TicketResponse> getAllTicketsOpenNode() {
        List<TicketResponse> ticketResponses = new ArrayList<TicketResponse>();

        OpenNodeResponse<List<OpenNodeCharge>> charges;
        try {
            charges = service.listCharges().execute().body();
            for (OpenNodeCharge charge : charges.getData()) {

                System.out.println(charge.getDescription() + " - " + charge.getAmount() + " - " + charge.getStatus());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        return ticketResponses;
    }

    public List<Tickets> getAllTickets(){
        return ticketRepository.findAll();
    }

    public OpenNodeCharge getTicketOpenNode(String ticketId) {

        OpenNodeCharge charge;
        try {
            charge = service.getCharge(ticketId).execute().body().getData();
            return charge;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public Tickets getTicket(Long ticketID){
        Tickets ticket = ticketRepository.getOne(ticketID);
        return ticket;
    }
}