package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.service.LoginDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login_data")
public class LoginDataController {

    private LoginDataService loginDataService;

    @Autowired
    public LoginDataController(LoginDataService loginDataService) {
        this.loginDataService = loginDataService;
    }

    @GetMapping("/id/{id}")
    public LoginData getLoginDataById(@PathVariable("id") int id) {
        return this.loginDataService.getLoginDataById(id);
    }

    @GetMapping("/login/{login}")
    public LoginData getLoginDataByLogin(@PathVariable("login") String login) {
        return this.loginDataService.getLoginDataByLogin(login);
    }

    @PostMapping
    public LoginData addLoginData(@RequestBody LoginData loginData) {
        return this.loginDataService.addLoginData(loginData);
    }

    @DeleteMapping("/admin")
    public void removeLoginData(@RequestBody LoginData loginData) {
        this.loginDataService.removeLoginData(loginData);
    }
}
