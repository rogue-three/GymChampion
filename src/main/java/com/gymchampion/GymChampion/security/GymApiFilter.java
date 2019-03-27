package com.gymchampion.GymChampion.security;


import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.SessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class GymApiFilter implements javax.servlet.Filter {

    private FilterHelper helper;

    @Autowired
    public GymApiFilter(FilterHelper helper) {
        this.helper = helper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String token = helper.getToken(servletRequest);

        LoginData data = helper.getLoginDataFromSession(token);

        Claims claims = Jwts.parser().setSigningKey(data.getPassword()).parseClaimsJws(token).getBody();
        servletRequest.setAttribute("claims", claims);
        filterChain.doFilter(servletRequest,servletResponse);
    }


}
