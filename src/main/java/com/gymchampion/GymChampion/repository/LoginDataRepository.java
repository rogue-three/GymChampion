package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    LoginData findByUser_Login(String login);
}
