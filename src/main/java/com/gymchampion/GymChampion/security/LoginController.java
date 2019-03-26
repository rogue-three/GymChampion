package com.gymchampion.GymChampion.security;


import com.gymchampion.GymChampion.security.exceptions.UncorrectPasswordException;
import com.gymchampion.GymChampion.security.exceptions.UserNotExistException;
import com.gymchampion.GymChampion.service.LoginDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginDataService loginDataService;

    @Autowired
    public LoginController(LoginDataService loginDataService) {
        this.loginDataService = loginDataService;
    }

    @PostMapping
    public String getTokenForUser(@RequestBody String login, @RequestBody String password) {

        try {
          loginDataService.validateUser(login, password);
        }
        catch(UncorrectPasswordException | UserNotExistException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "token";
    }
}
