package com.gymchampion.GymChampion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class GymChampionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymChampionApplication.class, args);
	}

}
