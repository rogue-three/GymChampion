package com.gymchampion.GymChampion.security;


import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.security.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.security.exceptions.UserNotExistException;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.SessionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginDataService loginDataService;
    private SessionService sessionService;

    @Autowired
    public LoginController(LoginDataService loginDataService, SessionService sessionService) {
        this.loginDataService = loginDataService;
        this.sessionService = sessionService;
    }

    @GetMapping("/{login}/{password}")
    public String getTokenForUser(@PathVariable String login, @PathVariable String password) {

        LoginData data;
        try {
           data = loginDataService.validateUserAndGetLoginData(login, password);
        }
        catch(UncorrectPasswordException | UserNotExistException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        long timeInMilisec = System.currentTimeMillis();

        String token = Jwts.builder()
                .setSubject(data.getUser().getLogin())
                .claim("role", data.getUserRole())
                .setIssuedAt(new Date(timeInMilisec))
                .setExpiration(new Date(timeInMilisec + 30000))  /// 30 sec test token
                .signWith(SignatureAlgorithm.HS384, data.getPassword())
                .compact();
        sessionService.addSession(
                new Session(token, new Date(System.currentTimeMillis()), true, data.getUser()));
        return token;

    }
}
