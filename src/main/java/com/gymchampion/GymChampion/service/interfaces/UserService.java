package com.gymchampion.GymChampion.service.interfaces;

import com.gymchampion.GymChampion.model.User;

import java.util.List;

public interface UserService {
    boolean doesUserExist(User user);

    void addUser(User user);

    User getUserByLogin(String login);

    List<User> getAllUsers();

    void updateUser(User user);

    void removeUser(String login);

    User patchUser(User userNewData);
}
