package com.gymchampion.GymChampion;

import com.gymchampion.GymChampion.security.GymApiFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class GymChampionApplication {

	private GymApiFilter gymApiFilter;

	@Autowired
	public GymChampionApplication(GymApiFilter gymApiFilter) {
		this.gymApiFilter = gymApiFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(GymChampionApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean<GymApiFilter> setFilter() {

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(gymApiFilter);
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/*"));
		return filterRegistrationBean;
	}
}
