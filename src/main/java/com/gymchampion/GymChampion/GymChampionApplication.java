package com.gymchampion.GymChampion;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GymChampionApplication {

	public static Logger logger = Logger.getLogger(GymChampionApplication.class);

	public static void main(String[] args) {
		logger.info("Gym Champion REST application starting...");
		SpringApplication.run(GymChampionApplication.class, args);
	}

}
