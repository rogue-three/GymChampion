package com.gymchampion.GymChampion.access_filters;

import com.gymchampion.GymChampion.model.LoginData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class AdminFilter implements javax.servlet.Filter {

    private FilterHelper helper;

    @Autowired
    public AdminFilter(FilterHelper helper) {
        this.helper = helper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String token = this.helper.getToken(servletRequest);

        LoginData data = this.helper.getLoginDataFromSession(token);

        Claims claims = Jwts.parser().setSigningKey(data.getPassword()).parseClaimsJws(token).getBody();
        servletRequest.setAttribute("claims", claims);

        if(!data.getUserRole().getRoleName().equals("ADMIN")) {
            throw new ServletException("Only Admin access!");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
