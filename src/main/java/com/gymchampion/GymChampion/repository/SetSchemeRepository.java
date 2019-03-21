package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetSchemeRepository extends JpaRepository<SetScheme, Integer> {

    List<SetScheme> findAllByTrainingTrainingId(@Param("training_id") int trainingId);
}
