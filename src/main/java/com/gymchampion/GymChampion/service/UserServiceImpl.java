package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.repository.UserRepository;
import com.gymchampion.GymChampion.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean doesUserExist(User user) {
        return this.getUserByLogin(user.getLogin()) != null;
    }

    @Override
    public void addUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userRepository.findUserByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void removeUser(String login) {
        User user = this.userRepository.findUserByLogin(login);
        this.userRepository.delete(user);
    }

    @Override
    public User patchUser(User userNewData) {
        User user = getUserByLogin(userNewData.getLogin());
        if (!userNewData.getNickname().equals(user.getNickname())){
            user.setNickname(userNewData.getNickname());
        }
        if (!userNewData.getBirthDate().equals(user.getBirthDate())){
            user.setBirthDate(userNewData.getBirthDate());
        }
        if (userNewData.getWeight() != user.getWeight()) {
            user.setWeight(userNewData.getWeight());
        }
        if (!userNewData.getGender().equals(user.getGender())){
            user.setGender(userNewData.getGender());
        }
        this.updateUser(user);
        return user;
    }
}
