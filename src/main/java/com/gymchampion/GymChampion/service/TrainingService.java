package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void addTraining(Training training) {
        this.trainingRepository.save(training);
    }

    public List<Training> getAllTrainings() {
        return this.trainingRepository.findAll();
    }

    public List<Training> getAllTrainingsFromActiveUsers() {
        return this.trainingRepository.findTrainingsByArchivized(false);
    }

    public List<Training> getAllArchivizedTrainings() {
        return this.trainingRepository.findTrainingsByArchivized(true);
    }
}
