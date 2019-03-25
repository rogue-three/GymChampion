package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    LoginData findByUser_Login(@Param("login") String login);
}
