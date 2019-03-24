package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    private GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<Gender> getAllGenders() {
        return this.genderRepository.findAll();
    }
}
