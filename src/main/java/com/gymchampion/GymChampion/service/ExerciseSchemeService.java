package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.ExerciseScheme;
import com.gymchampion.GymChampion.repository.ExerciseSchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseSchemeService {

    private ExerciseSchemeRepository exerciseSchemeRepository;

    @Autowired
    public ExerciseSchemeService(ExerciseSchemeRepository exerciseSchemeRepository) {
        this.exerciseSchemeRepository = exerciseSchemeRepository;
    }

    public List<ExerciseScheme> getAllExerciseSchemes() {
        return this.exerciseSchemeRepository.findAll();
    }

    public ExerciseScheme getExerciseSchemeById(int id) {
        Optional<ExerciseScheme> optionalExerciseScheme = this.exerciseSchemeRepository.findById(id);
        return optionalExerciseScheme.orElseGet(null);
    }

    public void addExerciseScheme(ExerciseScheme exerciseScheme) {
        this.exerciseSchemeRepository.save(exerciseScheme);
    }

    public boolean doesExerciseSchemeExist(ExerciseScheme exerciseScheme) {
        return this.exerciseSchemeRepository.findByExerciseSchemeName(exerciseScheme.getExerciseSchemeName()) != null;
    }

    public ExerciseScheme getExerciseSchemeByName(String exerciseSchemeName) {
        return this.exerciseSchemeRepository.findByExerciseSchemeName(exerciseSchemeName);
    }

    public void updateExerciseScheme(ExerciseScheme exerciseScheme) {
        this.exerciseSchemeRepository.save(exerciseScheme);
    }

    public void removeExerciseScheme(ExerciseScheme exerciseScheme) {
        this.exerciseSchemeRepository.delete(exerciseScheme);
    }
}
