package com.gymchampion.GymChampion.security;

import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signIn")
public class CreateUserController {

    private LoginDataService loginDataService;
    private UserService userService;

    @Autowired
    public CreateUserController(LoginDataService service, UserService userService) {
        this.loginDataService = service;
        this.userService = userService;
    }


    @PostMapping
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

}
