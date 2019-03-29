package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.service.GenderService;
import com.gymchampion.GymChampion.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;
    private GenderService genderService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public UserController(UserService userService,
                          GenderService genderService) {
        this.userService = userService;
        this.genderService = genderService;
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

    /*
     *   Gender handling
     */

    @GetMapping("/gender")
    public List<Gender> getAllGenders() {
        return this.genderService.getAllGenders();
    }
}
