package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceAlreadyExistsException;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.service.GenderService;
import com.gymchampion.GymChampion.service.UserService;
import com.gymchampion.GymChampion.util.UserBirthDateOnly;
import com.gymchampion.GymChampion.util.UserNicknameOnly;
import com.gymchampion.GymChampion.util.UserWeightOnly;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
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
    public ResponseEntity<?> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating User with login: %s.", user.getLogin()));
        if (this.userService.doesUserExist(user)) {
            logger.error(String.format("Unable to create. User with login %s already exist.", user.getLogin()));
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. User with login " +
                    user.getLogin() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.userService.addUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/users/{login}").buildAndExpand(user.getLogin()).toUri());
        logger.info("User created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all Users.");
        List<User> users = this.userService.getAllUsers();
        if (users.isEmpty()) {
            logger.error("Users not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{login}")
    public ResponseEntity<?> getUserByLogin(@PathVariable("login") String login) {
        logger.info(String.format("Fetching Users with login %s.", login));
        User user = this.userService.getUserByLogin(login);
        if (user == null) {
            logger.error(String.format("User with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("User with login " +
                    login + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/nickname/{login}")
    public ResponseEntity<?> setUserNickname(@RequestBody UserNicknameOnly userNicknameOnly,
                                             @PathVariable("login") String login) {
        logger.info(String.format("Setting user %s nickname to %s.", login, userNicknameOnly.getNickname()));
        User user = this.userService.getUserByLogin(login);
        if (user == null) {
            logger.error(String.format("Unable to set nickname. User with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set nickname. User with login " +
                    login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        user.setNickname(userNicknameOnly.getNickname());
        this.userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/birth_date/{login}")
    public ResponseEntity<?> setUserBirthDate(@RequestBody UserBirthDateOnly userBirthDateOnly,
                                             @PathVariable("login") String login) {
        logger.info(String.format("Setting user %s birth date to %s.", login, userBirthDateOnly.getUserBirthDate()));
        User user = this.userService.getUserByLogin(login);
        if (user == null) {
            logger.error(String.format("Unable to set birth date. User with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set birth date. User with login " +
                    login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        user.setBirthDate(userBirthDateOnly.getUserBirthDate());
        this.userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/gender/{login}")
    public ResponseEntity<?> setUserGender(@RequestBody Gender gender,
                                             @PathVariable("login") String login) {
        logger.info(String.format("Setting user %s gender to %s.", login, gender.getSex()));

        Gender genderFromDB = this.genderService.getGenderById(gender.getGenderId());
        if (genderFromDB == null) {
            logger.error(String.format("Unable to set gender. Gender with id %d not found.", gender.getGenderId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set gender. Gender with id " +
                    gender.getGenderId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        User user = this.userService.getUserByLogin(login);
        if (user == null) {
            logger.error(String.format("Unable to set gender. User with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to gender date. User with login " +
                    login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        user.setGender(gender);
        this.userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/weight/{login}")
    public ResponseEntity<?> setUserWeight(@RequestBody UserWeightOnly userWeightOnly,
                                             @PathVariable("login") String login) {
        logger.info(String.format("Setting user %s weight to %f.", login, userWeightOnly.getUserWeight()));
        User user = this.userService.getUserByLogin(login);
        if (user == null) {
            logger.error(String.format("Unable to set weight. User with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set weight. User with login " +
                    login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        user.setWeight(userWeightOnly.getUserWeight());
        this.userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<?> removeUser(@PathVariable("login") String login) {
        logger.info(String.format("Fetching & Deleting User with login %s.", login));
        User user = this.userService.getUserByLogin(login);
        if (user == null) {
            logger.error(String.format("Unable to delete. User with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. User with login " +
                    login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.userService.removeUser(login);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    /*
     *   Gender handling
     */

    @GetMapping("/gender")
    public ResponseEntity<List<Gender>> getAllGenders() {
        logger.info("Fetching genders.");
        List<Gender> genders = this.genderService.getAllGenders();
        if (genders.isEmpty()) {
            logger.error("Genders not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genders, HttpStatus.OK);
    }
}
