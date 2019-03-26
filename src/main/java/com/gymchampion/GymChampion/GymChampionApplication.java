package com.gymchampion.GymChampion;

import com.gymchampion.GymChampion.security.GymApiFilter;
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


	@Bean
	public FilterRegistrationBean setFilter() {

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new GymApiFilter());
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/*"));
		return filterRegistrationBean;
	}
}
