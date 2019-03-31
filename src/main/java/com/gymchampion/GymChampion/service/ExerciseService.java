package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Exercise;
import com.gymchampion.GymChampion.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public boolean doesExerciseExists(Exercise exercise) {
        return this.exerciseRepository.findByExerciseName(exercise.getExerciseName()) != null;
    }

    public List<Exercise> getAllExercises() {
        return this.exerciseRepository.findAll();
    }

    public Exercise getExerciseById(int id) {
        Optional<Exercise> optionalExercise = this.exerciseRepository.findById(id);
        return optionalExercise.orElse(null);
    }

    public Exercise getExerciseByName(String name) {
        return this.exerciseRepository.findByExerciseName(name);
    }

    public List<Exercise> getAllExerciseByBodyPart(String bodyPart) {
        return this.exerciseRepository.findAllByBodyPart_BodyPartName(bodyPart);
    }

    public List<Exercise> getListOfExerciseByScheme(String scheme) {
        return exerciseRepository.findAllByExerciseScheme_ExerciseSchemeName(scheme);
    }

    public List<Exercise> getAllExerciseByEquipment(String equipment) {
        return this.exerciseRepository.findAllByEquipment_EquipmentName(equipment);
    }

    public void addExercise(Exercise exercise) {
        this.exerciseRepository.save(exercise);
    }

    public void updateExercise(Exercise exercise) {
        this.exerciseRepository.save(exercise);
    }

    public void removeExerciseById(int id) {
        Exercise exercise = this.getExerciseById(id);
        this.exerciseRepository.delete(exercise);
    }

}
