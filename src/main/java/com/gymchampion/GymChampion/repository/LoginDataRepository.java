package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM login_data WHERE user_login = :login")
    LoginData findLoginDataByUserLogin(@Param("login") String login);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM login_data")
    int countOfLoginDataRecords();
}
