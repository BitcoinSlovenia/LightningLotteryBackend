package com.grmkris.lightningloterry;


import com.grmkris.lightningloterry.repository.RaffleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LightningLottery {

	@Autowired
	RaffleRepository raffleRepository;
	public static void main(String[] args) {
		SpringApplication.run(LightningLottery.class, args);

	}

}
