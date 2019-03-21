package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
}
