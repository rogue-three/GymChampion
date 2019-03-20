package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.repository.EquipmentRepository;
import com.gymchampion.GymChampion.repository.GenderRepository;
import org.springframework.stereotype.Component;

@Component
public class DBConstantsInitializer {

    public DBConstantsInitializer(GenderRepository genderRepository,
                                  EquipmentRepository equipmentRepository) {

        genderRepository.save(new Gender("male"));
        genderRepository.save(new Gender("female"));
        equipmentRepository.save(new Equipment("barbell"));
        equipmentRepository.save(new Equipment("dumbbell"));
        equipmentRepository.save(new Equipment("machine"));
        equipmentRepository.save(new Equipment("no equipment"));
    }


}
