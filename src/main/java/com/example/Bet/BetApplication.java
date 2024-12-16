package com.example.Bet;

import com.example.Bet.model.BetModel;
import com.example.Bet.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BetApplication.class, args);
	}

	@Autowired
	private BetService betService;


	@Override
	public void run(String... args) throws Exception {
		BetModel bet = new BetModel();
		bet.setBet_name("Real/Barca");
		bet.setBet_bid(300);
		bet.setBet_date("12.03.2023");

		betService.createBet(bet);
	}
}
