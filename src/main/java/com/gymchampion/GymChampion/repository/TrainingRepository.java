package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    List<Training> findTrainingsByArchivized(boolean isArchivized);
}
