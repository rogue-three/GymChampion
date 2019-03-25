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

    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUser(String login) {
        return this.userRepository.findUserByLogin(login);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User setUserNickname(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setNickname(user.getNickname());
        return this.userRepository.save(userToBeUpdated);
    }

    public User setUserBirthdate(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setBirthDate(user.getBirthDate());
        return this.userRepository.save(userToBeUpdated);
    }

    public User setUserGender(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setGender(user.getGender());
        return this.userRepository.save(userToBeUpdated);
    }

    public User setUserWeight(User user) {
        User userToBeUpdated = this.userRepository.findUserByLogin(user.getLogin());
        userToBeUpdated.setWeight(user.getWeight());
        return this.userRepository.save(userToBeUpdated);
    }

    public User archiveUser(String login) {
        User user = this.getUser(login);
        user.setArchived(true);
        return this.userRepository.save(user);
    }

    public List<User> getActiveUsers() {
        return this.userRepository.findUsersByArchived(false);
    }

    public List<User> getArchivedUsers() {
        return this.userRepository.findUsersByArchived(true);
    }
}
