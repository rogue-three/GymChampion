package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
