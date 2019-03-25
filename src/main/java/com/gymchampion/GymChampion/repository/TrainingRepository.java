package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    List<Training> findTrainingsByArchived(boolean isArchived);

    @Query(nativeQuery = true, value = "SELECT * FROM training WHERE user_login = :login")
    List<Training> findTrainingsByUserLogin(@Param("login") String login);
}
