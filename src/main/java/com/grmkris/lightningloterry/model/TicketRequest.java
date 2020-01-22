package com.grmkris.lightningloterry.model;

import lombok.Data;

// To je ticket, ki ga user izpolne na spletni strani. vnese svoj ime, email in številke
// to jaz sprejmem na backend
@Data
public class TicketRequest{

    String customer_name; // name of customer, or custom text
    String customer_email; //
    String description;
    Integer[] numbers;
    String callback_url;
    String success_url;

}