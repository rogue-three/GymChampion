package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    LoginData findByUser_Login(String login);

    LoginData findByPassword(String password);

    @Query(nativeQuery = true, value = "SELECT * FROM login_data WHERE archived = :isArchived")
    List<LoginData> findAllByArchived(@PathVariable("isArchived") boolean isArchived);
}
