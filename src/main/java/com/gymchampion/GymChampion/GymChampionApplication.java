package com.gymchampion.GymChampion;

import org.apache.log4j.Logger;
import com.gymchampion.GymChampion.access_filters.AdminFilter;
import com.gymchampion.GymChampion.access_filters.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class GymChampionApplication {

	public static Logger logger = Logger.getLogger(GymChampionApplication.class);

	private UserFilter userFilter;
	private AdminFilter adminFilter;

	@Autowired
	public GymChampionApplication(UserFilter userFilter, AdminFilter adminFilter)
	{
		this.adminFilter = adminFilter;
		this.userFilter = userFilter;
	}

	public static void main(String[] args) {
		logger.info("Gym Champion REST application starting.");
		SpringApplication.run(GymChampionApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<UserFilter> setUserFilter() {
		logger.info("Registering security filter for users.");
		FilterRegistrationBean<UserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(userFilter);
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/v1/users"));
		return filterRegistrationBean;
	}

	@Bean FilterRegistrationBean<AdminFilter> setAdminFilter() {
		logger.info("Registering security filter for admin.");
		FilterRegistrationBean<AdminFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(adminFilter);
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/user/*"));
		return filterRegistrationBean;
	}
}