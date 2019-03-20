package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }

    public User getUser(String login) {
        return this.userRepository.findUserByLogin(login);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void setUserNickname(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setNickname(user.getNickname());
        this.userRepository.save(userToBeUpdated);
    }

    public void setUserBirthdate(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setBirthDate(user.getBirthDate());
        this.userRepository.save(userToBeUpdated);
    }

    public void setUserGender(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setGender(user.getGender());
        this.userRepository.save(userToBeUpdated);
    }

    public void setUserWeight(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setWeight(user.getWeight());
        this.userRepository.save(userToBeUpdated);
    }

    public void archivizeUser(String login) {
        User user = this.getUser(login);
        user.setArchivized(true);
        this.userRepository.save(user);
    }

    public List<User> getActiveUsers() {
        return this.userRepository.findUsersByArchivized(false);
    }

    public List<User> getArchivizedUsers() {
        return this.userRepository.findUsersByArchivized(true);
    }
}
