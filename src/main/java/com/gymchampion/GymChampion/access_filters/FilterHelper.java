package com.gymchampion.GymChampion.access_filters;

import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Component
public class FilterHelper {

    private SessionService sessionService;
    private LoginDataService loginDataService;

    @Autowired
    public FilterHelper(LoginDataService loginDataService, SessionService sessionService) {
        this.loginDataService = loginDataService;
        this.sessionService = sessionService;
    }

    public LoginData getLoginDataFromSession(String token) throws ServletException {

        Session actualSession = this.sessionService.getSessionBySessionKey(token);
        if(actualSession == null) {
            throw new ServletException("Session terminate or deny access!");
        }
        String login = actualSession.getUser().getLogin();
        return loginDataService.getLoginDataByLogin(login);

    }


    public String getToken(ServletRequest servletRequest) throws ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorizationHeader = httpServletRequest.getHeader("authorization");
        if (authorizationHeader == null) {
            throw new ServletException("Login Error!");
        }
        int indexOfTokenStarted = 7;
        return authorizationHeader.substring(indexOfTokenStarted);
    }
}
