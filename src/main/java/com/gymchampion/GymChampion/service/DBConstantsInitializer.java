package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.*;
import com.gymchampion.GymChampion.repository.*;
import org.springframework.stereotype.Component;

@Component
public class DBConstantsInitializer {

    private Equipment barbellEquipment = new Equipment("barbell");

    private BodyPart chestBodyPart = new BodyPart("chest");
    private BodyPart quadsBodyPart = new BodyPart("quads");

    private ExerciseScheme upperBodyExScheme = new ExerciseScheme("upper body");
    private ExerciseScheme lowerBodyExScheme = new ExerciseScheme("lower body");

    public DBConstantsInitializer(GenderRepository genderRepository,
                                  ExerciseSchemeRepository exerciseSchemeRepository,
                                  EquipmentRepository equipmentRepository,
                                  BodyPartRepository bodyPartRepository,
                                  ExerciseRepository exerciseRepository) {

        initializeExerciseSchemeTableContent(exerciseSchemeRepository);
        initializeGenderTableContent(genderRepository);
        initializeEquipmentTableContent(equipmentRepository);
        initializeBodyPartTableContent(bodyPartRepository);
        initializeExerciseTableContent(exerciseRepository);
    }

    private void initializeExerciseSchemeTableContent(ExerciseSchemeRepository exerciseSchemeRepository) {
        exerciseSchemeRepository.save(upperBodyExScheme);
        exerciseSchemeRepository.save(lowerBodyExScheme);
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
        bodyPartRepository.save(chestBodyPart);
        bodyPartRepository.save(quadsBodyPart);
        bodyPartRepository.save(new BodyPart("hams"));
        bodyPartRepository.save(new BodyPart("arms"));
        bodyPartRepository.save(new BodyPart("calves"));
    }

    private void initializeExerciseTableContent(ExerciseRepository exerciseRepository) {
        Exercise barbellBenchPress = new Exercise("barbell bench press", 500, 500d);
        barbellBenchPress.setEquipment(barbellEquipment);
        barbellBenchPress.setBodyPart(chestBodyPart);
        barbellBenchPress.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(barbellBenchPress);

        Exercise squats = new Exercise("squats", 500, 300d);
        barbellBenchPress.setEquipment(barbellEquipment);
        barbellBenchPress.setBodyPart(quadsBodyPart);
        barbellBenchPress.setExerciseScheme(lowerBodyExScheme);
        exerciseRepository.save(barbellBenchPress);
    }

}
