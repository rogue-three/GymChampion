package com.gymchampion.GymChampion.restcontroller;


import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.exceptions.UserNotExistException;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.SessionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    /* user will login by example login_data json with only login and password:
        {
    "loginId": 1,
    "password": "krzychu1",
    "email": "example@gmail.com",
    "user": {
        "login": "krzychu",
        "nickname": null,
        "birthDate": null,
        "weight": null,
        "gender": null
    				},
    "userRole": {
        "id": 1,
        "roleName": "USER"
    },
    "archivized": false
}

     */
    @GetMapping
    @ResponseBody
    public String getTokenForUser(@RequestBody LoginData dataFromUser, HttpServletResponse response) {
        String login = dataFromUser.getUser().getLogin();
        String password = dataFromUser.getPassword();

        LoginData data;
        try {
           data = loginDataService.validateUserAndGetLoginData(login, password);
        }
        catch(UncorrectPasswordException | UserNotExistException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return e.getMessage();
        }
        long timeInMilisec = System.currentTimeMillis();

        String token = Jwts.builder()
                .setSubject(data.getUser().getLogin())
                .claim("role", data.getUserRole())
                .setIssuedAt(new Date(timeInMilisec))
                .setExpiration(new Date(timeInMilisec + 60000))  /// 60 sec test token
                .signWith(SignatureAlgorithm.HS384, data.getPassword())
                .compact();
        sessionService.addSession(
                new Session(token, new Date(System.currentTimeMillis()), true, data.getUser()));
        return token;

    }
}
