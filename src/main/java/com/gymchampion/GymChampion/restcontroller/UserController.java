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
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
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
    public User setUserNickname(@RequestBody User user) {
        return this.userService.setUserNickname(user);
    }

    @PutMapping("/birth_date")
    public User setUserBirthDate(@RequestBody User user) {
        return this.userService.setUserBirthDate(user);
    }

    @PutMapping("/gender")
    public User setUserGender(@RequestBody User user) {
        return this.userService.setUserGender(user);
    }

    @PutMapping("/weight")
    public User setUserWeight(@RequestBody User user) {
        return this.userService.setUserWeight(user);
    }

    @PutMapping("/archive/{login}")
    public User archiveUser(@PathVariable("login") String login) {
        return this.userService.archiveUser(login);
    }

    @GetMapping("/active")
    public List<User> getActiveUsers() {
        return this.userService.getActiveUsers();
    }

    @GetMapping("/archive")
    public List<User> getArchivedUsers() {
        return this.userService.getArchivedUsers();
    }
}
