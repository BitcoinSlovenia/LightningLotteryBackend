package com.grmkris.lightningloterry.model;

import lombok.Data;

// To je ticket, ki ga user izpolne na spletni strani. vnese svoj ime, email in številke
// to jaz sprejmem na backend
@Data
public class TicketRequest{

    String customerName; // name of customer, or custom text
    String customerEmail; //
    String description;
    String numbers;
    String callbackUrl;
    String successUrl;

}