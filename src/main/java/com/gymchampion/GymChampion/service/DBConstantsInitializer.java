package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.*;
import com.gymchampion.GymChampion.repository.*;
import org.springframework.stereotype.Component;

@Component
public class DBConstantsInitializer {

    private Equipment barbellEquipment = new Equipment("barbell");
    private Equipment noEquipmentEquipment = new Equipment("no equipment");

    private BodyPart chestBodyPart = new BodyPart("chest");
    private BodyPart quadsBodyPart = new BodyPart("quads");
    private BodyPart shouldersBodyPart = new BodyPart("shoulders");
    private BodyPart hamstringsBodyPart = new BodyPart("hamstrings");
    private BodyPart backBodyPart = new BodyPart("back");

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
        equipmentRepository.save(noEquipmentEquipment);
    }

    private void initializeBodyPartTableContent(BodyPartRepository bodyPartRepository) {
        bodyPartRepository.save(shouldersBodyPart);
        bodyPartRepository.save(backBodyPart);
        bodyPartRepository.save(chestBodyPart);
        bodyPartRepository.save(quadsBodyPart);
        bodyPartRepository.save(hamstringsBodyPart);
        bodyPartRepository.save(new BodyPart("arms"));
        bodyPartRepository.save(new BodyPart("calves"));
    }

    private void initializeExerciseTableContent(ExerciseRepository exerciseRepository) {
        Exercise barbellBenchPress = new Exercise("barbell bench press", 500, 500d);
        barbellBenchPress.setEquipment(barbellEquipment);
        barbellBenchPress.setBodyPart(chestBodyPart);
        barbellBenchPress.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(barbellBenchPress);

        Exercise squats = new Exercise("squats", 500, 500d);
        squats.setEquipment(barbellEquipment);
        squats.setBodyPart(quadsBodyPart);
        squats.setExerciseScheme(lowerBodyExScheme);
        exerciseRepository.save(squats);

        Exercise militaryPress = new Exercise("military press", 500, 300d);
        militaryPress.setEquipment(barbellEquipment);
        militaryPress.setBodyPart(shouldersBodyPart);
        militaryPress.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(militaryPress);

        Exercise deadLift = new Exercise("dead lift", 500, 800d);
        deadLift.setEquipment(barbellEquipment);
        deadLift.setBodyPart(hamstringsBodyPart);
        deadLift.setExerciseScheme(lowerBodyExScheme);
        exerciseRepository.save(deadLift);

        Exercise pullUps = new Exercise("pull ups", 200, 200d);
        pullUps.setEquipment(noEquipmentEquipment);
        pullUps.setBodyPart(backBodyPart);
        pullUps.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(pullUps);
    }



}
