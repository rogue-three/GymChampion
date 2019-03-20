package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.BodyPart;
import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.model.Exercise;
import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.repository.BodyPartRepository;
import com.gymchampion.GymChampion.repository.EquipmentRepository;
import com.gymchampion.GymChampion.repository.ExerciseRepository;
import com.gymchampion.GymChampion.repository.GenderRepository;
import org.springframework.stereotype.Component;

@Component
public class DBConstantsInitializer {

    private Equipment barbellEquipment = new Equipment("barbell");

    public DBConstantsInitializer(GenderRepository genderRepository,
                                  EquipmentRepository equipmentRepository,
                                  BodyPartRepository bodyPartRepository,
                                  ExerciseRepository exerciseRepository) {

        initializeGenderTableContent(genderRepository);
        initializeEquipmentTableContent(equipmentRepository);
        initializeBodyPartTableContent(bodyPartRepository);

    }


    private void initializeGenderTableContent(GenderRepository genderRepository){
        genderRepository.save(new Gender("male"));
        genderRepository.save(new Gender("female"));
    }

    private void initializeEquipmentTableContent(EquipmentRepository equipmentRepository ){
        equipmentRepository.save(barbellEquipment);
        equipmentRepository.save(new Equipment("dumbbell"));
        equipmentRepository.save(new Equipment("machine"));
        equipmentRepository.save(new Equipment("no equipment"));
    }

    private void initializeBodyPartTableContent(BodyPartRepository bodyPartRepository) {
        bodyPartRepository.save(new BodyPart("shoulders"));
        bodyPartRepository.save(new BodyPart("back"));
        bodyPartRepository.save(new BodyPart("chest"));
        bodyPartRepository.save(new BodyPart("quads"));
        bodyPartRepository.save(new BodyPart("hams"));
        bodyPartRepository.save(new BodyPart("arms"));
        bodyPartRepository.save(new BodyPart("calves"));
    }

    private void initializeExerciseTableContent(ExerciseRepository exerciseRepository) {
        Exercise barbellBenchPress = new Exercise("barbellBenchPress", 500, 500d);
        barbellBenchPress.setEquipment(barbellEquipment);
        barbellBenchPress.setBodyPart();
        barbellBenchPress.setExerciseScheme();

        exerciseRepository.save(barbellBenchPress);
    }

}
