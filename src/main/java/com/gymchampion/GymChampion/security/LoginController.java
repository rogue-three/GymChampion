package com.gymchampion.GymChampion.security;


import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.security.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.security.exceptions.UserNotExistException;
import com.gymchampion.GymChampion.service.LoginDataService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginDataService loginDataService;

    @Autowired
    public LoginController(LoginDataService loginDataService) {
        this.loginDataService = loginDataService;
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

        return
        Jwts.builder()
                .setSubject(data.getUser().getLogin())
                .claim("role", data.getUserRole())
                .setIssuedAt(new Date(timeInMilisec))
                .setExpiration(new Date(timeInMilisec + 30000))  /// 30 sec test token
                .signWith(SignatureAlgorithm.HS384, data.getPassword())
                .compact();
    }
}
