package com.gymchampion.GymChampion;

import com.gymchampion.GymChampion.security.AdminFilter;
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
	private AdminFilter adminFilter;

	@Autowired
	public GymChampionApplication(GymApiFilter gymApiFilter, AdminFilter adminFilter)
	{
		this.adminFilter = adminFilter;
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

	@Bean FilterRegistrationBean<AdminFilter> setAdminFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(adminFilter);
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/admin/*"));
		return filterRegistrationBean;
	}
}