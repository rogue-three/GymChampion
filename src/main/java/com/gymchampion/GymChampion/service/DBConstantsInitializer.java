package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.*;
import com.gymchampion.GymChampion.repository.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DBConstantsInitializer {

    private ExerciseScheme upperBodyExScheme = new ExerciseScheme("upper body");
    private ExerciseScheme lowerBodyExScheme = new ExerciseScheme("lower body");

    private Gender maleGender = new Gender("male");
    private Gender femaleGender = new Gender("female");

    private Equipment barbellEquipment = new Equipment("barbell");
    private Equipment noEquipmentEquipment = new Equipment("no equipment");

    private BodyPart chestBodyPart = new BodyPart("chest");
    private BodyPart quadsBodyPart = new BodyPart("quads");
    private BodyPart shouldersBodyPart = new BodyPart("shoulders");
    private BodyPart hamstringsBodyPart = new BodyPart("hamstrings");
    private BodyPart backBodyPart = new BodyPart("back");

    public DBConstantsInitializer(GenderRepository genderRepository,
                                  ExerciseSchemeRepository exerciseSchemeRepository,
                                  EquipmentRepository equipmentRepository,
                                  BodyPartRepository bodyPartRepository,
                                  ExerciseRepository exerciseRepository,
                                  UserRepository userRepository,
                                  TrainingRepository trainingRepository) {

        initializeExerciseSchemeTableContent(exerciseSchemeRepository);
        initializeGenderTableContent(genderRepository);
        initializeEquipmentTableContent(equipmentRepository);
        initializeBodyPartTableContent(bodyPartRepository);
        initializeExerciseTableContent(exerciseRepository);
        initializeUsers(userRepository);
        initializeTraining(trainingRepository);
    }

    private void initializeExerciseSchemeTableContent(ExerciseSchemeRepository exerciseSchemeRepository) {
        exerciseSchemeRepository.save(upperBodyExScheme);
        exerciseSchemeRepository.save(lowerBodyExScheme);
    }

    private void initializeGenderTableContent(GenderRepository genderRepository){
        genderRepository.save(maleGender);
        genderRepository.save(femaleGender);
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

    private void initializeUsers(UserRepository userRepository) {
        User mihuUser = new User("Mihu", "the mihu", 88.5, false);
        mihuUser.setBirthDate(new Date(75, 8, 10));
        mihuUser.setGender(maleGender);
        userRepository.save(mihuUser);

        User krzychuUser = new User("Krzychu", "duży krzychu", 85, false);
        krzychuUser.setBirthDate(new Date(90, 3, 12));
        krzychuUser.setGender(maleGender);
        userRepository.save(krzychuUser);

        User jodlaUser = new User("Jodla", "scrum maestro", 75, false);
        jodlaUser.setBirthDate(new Date(87, 5, 21));
        jodlaUser.setGender(maleGender);
        userRepository.save(jodlaUser);

        User zosiaUser = new User("Zosia", "zocha", 62, false);
        zosiaUser.setBirthDate(new Date(91, 11, 11));
        zosiaUser.setGender(femaleGender);
        userRepository.save(zosiaUser);
    }
//TODO change data format to timestamp
    private void initializeTraining(TrainingRepository trainingRepository) {
        Training tr001 = new Training(false);
        tr001.setTrainingDate(new Date(2019, 2, 20));
        trainingRepository.save(tr001);

        Training tr002 = new Training(false);
        tr002.setTrainingDate(new Date(2019, 2, 22));
        trainingRepository.save(tr002);

        Training tr003 = new Training(false);
        tr003.setTrainingDate(new Date(2019, 2, 24));
        trainingRepository.save(tr003);

        Training tr004 = new Training(false);
        tr004.setTrainingDate(new Date(2019, 2, 25));
        trainingRepository.save(tr004);

        Training tr005 = new Training(false);
        tr005.setTrainingDate(new Date(2019, 2, 26));
        trainingRepository.save(tr005);

        Training tr006 = new Training(false);
        tr006.setTrainingDate(new Date(2019, 1, 12));
        trainingRepository.save(tr006);

        Training tr007 = new Training(false);
        tr007.setTrainingDate(new Date(2019, 1, 13));
        trainingRepository.save(tr007);

        Training tr008 = new Training(false);
        tr008.setTrainingDate(new Date(2019, 1, 14));
        trainingRepository.save(tr008);

        Training tr009 = new Training(false);
        tr009.setTrainingDate(new Date(2019, 1, 20));
        trainingRepository.save(tr009);

        Training tr010 = new Training(false);
        tr010.setTrainingDate(new Date(2019, 3, 11));
        trainingRepository.save(tr010);

        Training tr011 = new Training(false);
        tr011.setTrainingDate(new Date(2019, 3, 12));
        trainingRepository.save(tr011);

        Training tr012 = new Training(false);
        tr012.setTrainingDate(new Date(2019, 3, 13));
        trainingRepository.save(tr012);

        Training tr013 = new Training(false);
        tr013.setTrainingDate(new Date(2019, 3, 14));
        trainingRepository.save(tr013);

        Training tr014 = new Training(false);
        tr014.setTrainingDate(new Date(2019, 3, 15));
        trainingRepository.save(tr014);
    }



}
