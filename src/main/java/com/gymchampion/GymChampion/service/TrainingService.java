package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.repository.TrainingRepository;
import com.gymchampion.GymChampion.util.TrainingDaysCount;
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

    public Training getTrainingById(int id) {
        Optional<Training> optionalTraining = this.trainingRepository.findById(id);
        Training training = new Training();

        if(optionalTraining.isPresent()) {
            training = optionalTraining.get();
        }
        return training;
    }

    public List<Training> getAllTrainingsFromActiveUsers() {
        return this.trainingRepository.findTrainingsByArchived(false);
    }

    public List<Training> getAllArchivedTrainings() {
        return this.trainingRepository.findTrainingsByArchived(true);
    }

    public List<Training> getTrainingsByUserLogin(String login) {
        return this.trainingRepository.findAllByUser_Login(login);
    }

    public void updateTraining(Training training) {
        this.trainingRepository.save(training);
    }

    public void updateTrainings(List<Training> trainings) {
        this.trainingRepository.saveAll(trainings);
    }

    public void removeTraining(Training training) {
        this.trainingRepository.delete(training);
    }

    public void removeTrainings(List<Training> trainings) {
        this.trainingRepository.deleteAll(trainings);
    }

    public TrainingDaysCount countTrainingsByUserLogin(String login) {
        int trainedDays = this.getTrainingsByUserLogin(login).size();
        return new TrainingDaysCount(trainedDays);
    }
}
