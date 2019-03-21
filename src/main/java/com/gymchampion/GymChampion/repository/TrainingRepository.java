package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    List<Training> findTrainingsByArchivized(boolean isArchivized);

    @Query(nativeQuery = true, value = "SELECT training.training_id, training.archivized, training.training_date, training.user_weight " +
            "FROM training RIGHT JOIN gym_user_training ON training.training_id = gym_user_training.training_id " +
            "WHERE gym_user_training.user_login = :login")
    List<Training> findTrainingsByUserLogin(@Param("login") String login);
}
