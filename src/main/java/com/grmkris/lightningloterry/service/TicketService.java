package com.grmkris.lightningloterry.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.grmkris.lightningloterry.model.TicketRequest;
import com.grmkris.lightningloterry.model.TicketResponse;

import org.brunocvcunha.opennode.api.OpenNodeService;
import org.brunocvcunha.opennode.api.OpenNodeServiceFactory;
import org.brunocvcunha.opennode.api.model.OpenNodeCharge;
import org.brunocvcunha.opennode.api.model.OpenNodeCreateCharge;
import org.brunocvcunha.opennode.api.model.OpenNodeCurrency;
import org.brunocvcunha.opennode.api.model.OpenNodeResponse;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private static Long TICKET_AMOUNT = 200L;

    OpenNodeService service = OpenNodeServiceFactory.buildClient("b95d29ac-4ce9-45c9-ab9e-8767b35a01de");

    public TicketResponse newTicket(TicketRequest ticketRequest) {

        try {

            // TODO: shrani numbers v bazo oziroma celoten ticketRequest
            OpenNodeCreateCharge createCharge = OpenNodeCreateCharge.builder().orderId("internal id")
                    .description(ticketRequest.getDescription()).amount(TICKET_AMOUNT)
                    .callbackUrl(ticketRequest.getCallback_url()).customerEmail(ticketRequest.getCustomer_email())
                    .customerName(ticketRequest.getCustomer_name())
                    // .currency(OpenNodeCurrency.EUR) // default is satoshis
                    .build();

            OpenNodeCharge createdCharge = service.createCharge(createCharge).execute().body().getData();

            TicketResponse ticketResponse = TicketResponse.builder().amount(TICKET_AMOUNT)
                    .customer_email(ticketRequest.getCustomer_email()).customer_name(createdCharge.getName())
                    .fiat_value(createdCharge.getFiatValue()).lightningInvoice(createdCharge.getLightningInvoice())
                    .numbers(ticketRequest.getNumbers()).openNodeId(createdCharge.getId())
                    .status(createdCharge.getStatus()).build();

            return ticketResponse;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            // API excpetion poglej proper exception handling
            e.printStackTrace();
        }
        return null;

    }

    public List<TicketResponse> getAllTickets() {
        List<TicketResponse> ticketResponses = new ArrayList<TicketResponse>();

        OpenNodeResponse<List<OpenNodeCharge>> charges = service.listCharges().execute().body();
        for (OpenNodeCharge charge : charges.getData()) {

            System.out.println(charge.getDescription() + " - " + charge.getAmount() + " - " + charge.getStatus());
        }
        
        return ticketResponses;
    }

    public OpenNodeCharge getTicket(String ticketId) {

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
}