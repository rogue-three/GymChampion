package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM login_data WHERE login_data.user_login = :login")
    LoginData findLoginDataByUserLogin(@Param("login") String login);
}
