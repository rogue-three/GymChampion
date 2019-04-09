package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.repository.SetSchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetSchemeService {

    private SetSchemeRepository setSchemeRepository;
    private final int R_MAX_CONSTANT = 30;

    @Autowired
    public SetSchemeService(SetSchemeRepository repository) {
        this.setSchemeRepository = repository;
    }

    public void addSchemeToRepository(SetScheme scheme)  {
        this.setSchemeRepository.save(scheme);

    }

    public void addSchemeListToRepository(List<SetScheme> schemeList) {
        this.setSchemeRepository.saveAll(schemeList);
    }

    public List<SetScheme> getAllSetSchemes() {
        return this.setSchemeRepository.findAll();
    }

    public SetScheme getSetSchemeById(int id) {
        Optional<SetScheme> optionalSetScheme = this.setSchemeRepository.findById(id);
        return optionalSetScheme.orElse(null);
    }

    public List<SetScheme> getSchemesByTrainingId(int trainingId) {
        return this.setSchemeRepository.findAllByTrainingTrainingId(trainingId);
    }

    public List<SetScheme> getAllSetSchemesByUser(String userLogin) {
        return this.setSchemeRepository.getAllByTraining_User_Login(userLogin);
    }

    public List<SetScheme> getSetSchemeByExerciesIdAndUserLogin(int exerciseId, String userLogin) {
        return this.setSchemeRepository.getSetSchemeByExercise_ExerciseIdAndTraining_User_Login(exerciseId, userLogin);
    }

    public SetScheme getSetSchemeWithMaxWeightByExerciseId(int exerciseId, String login) {
        return this.setSchemeRepository.getSetSchemeWithMaxWeightByExerciseId(exerciseId, login);
    }

    public SetScheme getSetSchemeWithHighestExpected1RmaxByExerciseIdAndUserLogin(int exerciseId, String userLogin) {
        SetScheme highest1rmSetScheme = getSetSchemeByExerciesIdAndUserLogin(exerciseId, userLogin).get(0);
        for(SetScheme setScheme: getSetSchemeByExerciesIdAndUserLogin(exerciseId, userLogin)) {
            if(count1RMax(setScheme)>= count1RMax(highest1rmSetScheme)){
                highest1rmSetScheme = setScheme;
            }
        }
        return highest1rmSetScheme;
    }

    private double count1RMax(SetScheme setScheme){
        if (setScheme.getReps() < 2) {
            return setScheme.getWeight();
        }
        return setScheme.getWeight() * (1 + ((double) setScheme.getReps()/ R_MAX_CONSTANT));
    }

    public Double getTotalLoadFromTrainingBy(int trainingId) {
        double totalLoad = 0;
        for(SetScheme exerciseSet: getSchemesByTrainingId(trainingId)) {
            totalLoad += exerciseSet.getWeight() * exerciseSet.getReps();
        }
        return totalLoad;
    }

    public Double getTotalLoadFromAllTrainingsByUser(String userLogin) {
        double totalLoad = 0;
        for(SetScheme exerciseSet: getAllSetSchemesByUser(userLogin)) {
            totalLoad += exerciseSet.getWeight() * exerciseSet.getReps();
        }
        return totalLoad;
    }




    public void removeSetScheme(SetScheme setScheme) {
        this.setSchemeRepository.delete(setScheme);
    }

    public void removeSetSchemes(List<SetScheme> setSchemes) {
        this.setSchemeRepository.deleteAll(setSchemes);
    }
}
