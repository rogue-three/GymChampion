package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        this.userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{login}")
    public User getUser(@PathVariable("login") String login) {
        return this.userService.getUser(login);
    }

    @PutMapping("/nickname")
    public void setUserNickname(@RequestBody User user) {
        this.userService.setUserNickname(user);
    }

    @PutMapping("/birthdate")
    public void setUserBirthdate(@RequestBody User user) {
        this.userService.setUserBirthdate(user);
    }

    @PutMapping("/gender")
    public void setUserGender(@RequestBody User user) {
        this.userService.setUserGender(user);
    }

    @PutMapping("/weight")
    public void setUserWeight(@RequestBody User user) {
        this.userService.setUserWeight(user);
    }

    @PutMapping("/archivize/{login}")
    public void archivizeUser(@PathVariable("login") String login) {
        this.userService.archivizeUser(login);
    }

    @GetMapping("/active")
    public List<User> getActiveUsers() {
        return this.userService.getActiveUsers();
    }

    @GetMapping("/archive")
    public List<User> getArchivizedUsers() {
        return this.userService.getArchivizedUsers();
    }
}
