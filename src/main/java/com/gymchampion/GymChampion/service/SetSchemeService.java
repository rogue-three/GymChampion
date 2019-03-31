package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.repository.SetSchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SetSchemeService {

    private SetSchemeRepository setSchemeRepository;

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

    public SetScheme getSetSchemeWithMaxWeightByExerciseId(int exerciseId, String login) {
        return this.setSchemeRepository.getSetSchemeWithMaxWeightByExerciseId(exerciseId, login);
    }

    public void removeSetScheme(SetScheme setScheme) {
        this.setSchemeRepository.delete(setScheme);
    }

    public void removeSetSchemes(List<SetScheme> setSchemes) {
        this.setSchemeRepository.deleteAll(setSchemes);
    }
}
