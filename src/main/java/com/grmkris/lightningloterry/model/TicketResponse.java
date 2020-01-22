package com.grmkris.lightningloterry.model;

import org.brunocvcunha.opennode.api.model.OpenNodeChargeStatus;
import org.brunocvcunha.opennode.api.model.OpenNodeLightningInvoice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {

    String ticket_id; //naš interni id, order_id za openNode
    String openNodeId; //openNode id
    String customer_name; // name of customer, or custom text
    String customer_email; //
    Integer[] numbers;
    OpenNodeChargeStatus status; // unpaid, paid, processing, win, lose
    Double amount; //cena ticketa (200 satoshi)
    Double fiat_value;
    OpenNodeLightningInvoice lightningInvoice;

}

/*
PRIMER RESPONSE CODE:

{
"data":{
"id":"440148a4-f881-4508-b160-8e44c5d70c30"
"name":"kristjan"
"description":"description"
"created_at":1579635580
"status":"unpaid"
"callback_url":"callbackUrl"
"success_url":"sucessUrl"
"order_id":"ticketID1"
"notes":"Order: ticketID1 Customer Email: kristjan.grm1@gmail.com"
"currency":"BTC"
"source_fiat_value":0
"fiat_value":0.01555398
"auto_settle":false
"notif_email":"kristjan.grm1@gmail.com"
"lightning_invoice":{
"expires_at":1579639180
"payreq":"LNBC2U1P0ZW5MUPP59TTGJV0NKYU3SXNRKENDVXDFGUM8S2KS9JJ4QXFJCEALMAT9P99SDQJV3JHXCMJD9C8G6T0DCCQZPG36EMVC0NU7PVFDP25D63L3N8PR74EZZJE82YTPLN2X3Q3N0P5QDX2G05Z5QRKATFDR5Q5Y6VGRMFUG3QUCY0KKUNUV9L5WHUDGZMGKSQ6YCWUC"
}
"chain_invoice":{
"address":"3AbQYwwnp53wmQdKhsAgkUopUMxSTBbyCA"
}
"address":"3AbQYwwnp53wmQdKhsAgkUopUMxSTBbyCA"
"amount":200
"uri":"bitcoin:3AbQYwwnp53wmQdKhsAgkUopUMxSTBbyCA?amount=0.00000200&label=description&lightning=LNBC2U1P0ZW5MUPP59TTGJV0NKYU3SXNRKENDVXDFGUM8S2KS9JJ4QXFJCEALMAT9P99SDQJV3JHXCMJD9C8G6T0DCCQZPG36EMVC0NU7PVFDP25D63L3N8PR74EZZJE82YTPLN2X3Q3N0P5QDX2G05Z5QRKATFDR5Q5Y6VGRMFUG3QUCY0KKUNUV9L5WHUDGZMGKSQ6YCWUC"
}

*/