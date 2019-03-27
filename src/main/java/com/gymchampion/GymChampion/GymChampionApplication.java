package com.gymchampion.GymChampion;

import com.gymchampion.GymChampion.access.filters.AdminFilter;
import com.gymchampion.GymChampion.access.filters.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class GymChampionApplication {

	private UserFilter userFilter;
	private AdminFilter adminFilter;

	@Autowired
	public GymChampionApplication(UserFilter userFilter, AdminFilter adminFilter)
	{
		this.adminFilter = adminFilter;
		this.userFilter = userFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(GymChampionApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean<UserFilter> setUserFilter() {

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(userFilter);
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/*"));
		return filterRegistrationBean;
	}

	@Bean FilterRegistrationBean<AdminFilter> setAdminFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(adminFilter);
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/admin/*"));
		return filterRegistrationBean;
	}
}