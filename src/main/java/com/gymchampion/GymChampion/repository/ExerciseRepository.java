package com.gymchampion.GymChampion.repository;


import com.gymchampion.GymChampion.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {


    List<Exercise> findAllByExerciseScheme_ExerciseSchemeName(String name);



}
