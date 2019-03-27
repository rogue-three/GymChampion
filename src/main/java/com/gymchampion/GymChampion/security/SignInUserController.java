package com.gymchampion.GymChampion.security;

import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Role;
import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.RoleService;
import com.gymchampion.GymChampion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signIn")
public class SignInUserController {

    private LoginDataService loginDataService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public SignInUserController(LoginDataService service, UserService userService, RoleService roleService) {
        this.loginDataService = service;
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostMapping
    public User addUser(@RequestBody LoginData data) {
        loginDataService.addLoginData(data);
        Role user = roleService.getRoleByRoleName("USER");
        data.setUserRole(user);
        return this.userService.addUser(data.getUser());
    }

}
