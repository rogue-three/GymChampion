package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.exceptions.UserNotExistException;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.SessionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    private LoginDataService loginDataService;
    private SessionService sessionService;
    private static Logger logger = GymChampionApplication.logger;

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
}*/

    @GetMapping
    public ResponseEntity<?> getTokenForUser(@RequestBody LoginData userLoginData, HttpServletResponse response) {
        String userLogin = userLoginData.getUser().getLogin();
        logger.info(String.format("Fetching access token for user with login %s", userLogin));
        String password = userLoginData.getPassword();
        LoginData data;

        try {
           data = this.loginDataService.validateUserAndGetLoginData(userLogin, password);
        }
        catch(UncorrectPasswordException | UserNotExistException e) {
            logger.error(String.format("Access token for user with login %s not found.", userLogin));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Access token for user with login " +
                    userLogin + " not found.").getMessage(), HttpStatus.UNAUTHORIZED);
        }
        long timeInMilisec = System.currentTimeMillis();

        String token = Jwts.builder()
                .setSubject(data.getUser().getLogin())
                .claim("role", data.getUserRole())
                .setIssuedAt(new Date(timeInMilisec))
                .setExpiration(new Date(timeInMilisec + 60000))  /// 60 sec test token
                .signWith(SignatureAlgorithm.HS384, data.getPassword())
                .compact();
        this.sessionService.addSession(
                new Session(token, new Date(System.currentTimeMillis()), true, data.getUser()));
        return new ResponseEntity<>(token, HttpStatus.OK);  // should be packed into object to obtain json
    }
}
