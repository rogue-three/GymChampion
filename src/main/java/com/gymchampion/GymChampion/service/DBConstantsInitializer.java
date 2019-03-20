package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.repository.GenderRepository;
import org.springframework.stereotype.Component;

@Component
public class DBConstantsInitializer {

    public DBConstantsInitializer(GenderRepository genderRepository) {
        genderRepository.save(new Gender("male"));
        genderRepository.save(new Gender("female"));
    }


}
