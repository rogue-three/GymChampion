package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    LoginData findByUser_Login(String login);

    LoginData findByPassword(String password);

    List<LoginData> findAllByArchived(boolean isArchived);
}
