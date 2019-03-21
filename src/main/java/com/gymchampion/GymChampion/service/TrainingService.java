package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void addTraining(Training training) {
        double userWeight = training.getUser().getWeight();
        training.setUserBodyWeight(userWeight);
        this.trainingRepository.save(training);
    }

    public List<Training> getAllTrainings() {
        return this.trainingRepository.findAll();
    }

    public Training getTraining(int id) {
        Optional<Training> optionalTraining = this.trainingRepository.findById(id);
        Training training = new Training();

        if(optionalTraining.isPresent()) {
            training = optionalTraining.get();
        }
        return training;
    }

    public List<Training> getAllTrainingsFromActiveUsers() {
        return this.trainingRepository.findTrainingsByArchivized(false);
    }

    public List<Training> getAllArchivizedTrainings() {
        return this.trainingRepository.findTrainingsByArchivized(true);
    }

    public List<Training> getTrainingsByUserLogin(String login) {
        return this.trainingRepository.findTrainingsByUserLogin(login);
    }

    public void archivizeTraining(int id) {
        Optional<Training> optionalTraining = this.trainingRepository.findById(id);
        Training training;
        if (optionalTraining.isPresent()) {
            training = optionalTraining.get();
            training.setArchivized(true);
            this.trainingRepository.save(training);
        }
    }

    public int countTrainingsByUserLogin(String login) {
        return this.trainingRepository.findTrainingsByUserLogin(login).size();
    }
}
