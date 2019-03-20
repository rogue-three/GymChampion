package com.gymchampion.GymChampion.Service;

import com.gymchampion.GymChampion.model.Exercise;
import com.gymchampion.GymChampion.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private ExerciseRepository repository;

    @Autowired
    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public List<Exercise> getListOfExerciseByScheme(String scheme) {
        return repository.findAllByExerciseScheme_ExerciseSchemeName(scheme);
    }
}
