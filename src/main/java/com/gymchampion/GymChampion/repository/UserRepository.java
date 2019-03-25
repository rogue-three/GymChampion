package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByLogin(String login);

    List<User> findUsersByArchived(boolean isArchived);
}
