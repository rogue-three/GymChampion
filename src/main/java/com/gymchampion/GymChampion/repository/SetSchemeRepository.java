package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetSchemeRepository extends JpaRepository<SetScheme, Integer> {

    List<SetScheme> findAllByTrainingTrainingId(int trainingId);

    @Query(nativeQuery = true, value= "SELECT set_scheme.set_scheme_id, set_scheme.reps, set_scheme.weight, " +
            "set_scheme.exercise_id, set_scheme.training_id " +
            "FROM set_scheme " +
            "INNER JOIN training t on set_scheme.training_id = t.training_id " +
            "WHERE set_scheme.exercise_id = :exerciseId AND t.user_login = :login " +
            "ORDER BY set_scheme.weight DESC LIMIT 1")
    SetScheme getSetSchemeWithMaxWeightByExerciseId( @Param("exerciseId") int exerciseId, @Param("login") String userLogin);

    List<SetScheme> getSetSchemeByExercise_ExerciseIdAndTraining_User_Login(int exerciseId, String userLogin);
}
