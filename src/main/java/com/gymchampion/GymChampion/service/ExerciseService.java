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

    public List<Exercise> getAllExercises() {
        return this.exerciseRepository.findAll();
    }

    public Exercise getExerciseById(int id) {
        Optional<Exercise> optionalExercise = this.exerciseRepository.findById(id);
        return optionalExercise.orElseGet(Exercise::new);
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

    public Exercise addExercise(Exercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    public Exercise setExerciseMaxReps(Exercise exercise) {
        Exercise exerciseToUpdate = this.getExerciseById(exercise.getExerciseId());
        exerciseToUpdate.setMaxReps(exercise.getMaxReps());
        return this.exerciseRepository.save(exerciseToUpdate);
    }

    public Exercise setExerciseMaxWeight(Exercise exercise) {
        Exercise exerciseToUpdate = this.getExerciseById(exercise.getExerciseId());
        exerciseToUpdate.setMaxWeight(exercise.getMaxWeight());
        return this.exerciseRepository.save(exerciseToUpdate);
    }

    public Exercise setExerciseEquipment(Exercise exercise) {
        Exercise exerciseToUpdate = this.getExerciseById(exercise.getExerciseId());
        exerciseToUpdate.setEquipment(exercise.getEquipment());
        return this.exerciseRepository.save(exerciseToUpdate);
    }

}
