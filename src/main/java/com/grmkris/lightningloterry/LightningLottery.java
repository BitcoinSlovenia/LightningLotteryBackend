package com.grmkris.lightningloterry;


import java.sql.Timestamp;
import java.util.Date;

import com.grmkris.lightningloterry.model.database.Raffle;
import com.grmkris.lightningloterry.repository.RaffleRepository;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LightningLottery {

	@Autowired
	RaffleRepository raffleRepository;
	public static void main(String[] args) {
		SpringApplication.run(LightningLottery.class, args);
	}

}
