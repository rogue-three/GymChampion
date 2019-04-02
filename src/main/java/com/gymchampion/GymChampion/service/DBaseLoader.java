package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.model.*;
import com.gymchampion.GymChampion.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DBaseLoader implements CommandLineRunner {

    private GymChampionApplication gymChampionApplication;

    private  GenderRepository genderRepository;
    private  ExerciseSchemeRepository exerciseSchemeRepository;
    private  EquipmentRepository equipmentRepository;
    private  UserRepository userRepository;
    private  TrainingRepository trainingRepository;
    private  ExerciseRepository exerciseRepository;
    private  SetSchemeRepository setSchemeRepository;
    private  BodyPartRepository bodyPartRepository;
    private RoleRepository roleRepository;

    private ExerciseScheme upperBodyExScheme = new ExerciseScheme("upper_body");
    private ExerciseScheme lowerBodyExScheme = new ExerciseScheme("lower_body");

    private Gender maleGender = new Gender("male");
    private Gender femaleGender = new Gender("female");

    private Equipment barbellEquipment = new Equipment("barbell");
    private Equipment noEquipmentEquipment = new Equipment("no_equipment");
    private Equipment dumbbellEquipment = new Equipment("dumbbell");
    private Equipment machineEquipment = new Equipment("machine");

    private BodyPart chestBodyPart = new BodyPart("chest");
    private BodyPart quadsBodyPart = new BodyPart("quads");
    private BodyPart shouldersBodyPart = new BodyPart("shoulders");
    private BodyPart hamstringsBodyPart = new BodyPart("hamstrings");
    private BodyPart backBodyPart = new BodyPart("back");
    private BodyPart calvesBodyPart = new BodyPart("calves");
    private BodyPart armsBodyPart = new BodyPart("arms");

    private Exercise barbellBenchPress;
    private Exercise squats;
    private Exercise militaryPress;
    private Exercise pullUps;

    private Role user;
    private Role admin;

    private User mihuUser;
    private User krzychuUser;
    private User jodlaUser;
    private User zosiaUser;

    private Training tr001;
    private Training tr002;
    private Training tr003;
    private Training tr004;
    private Training tr005;
    private Training tr006;
    private Training tr007;


    @Autowired
    public DBaseLoader(GymChampionApplication gymChampionApplication, GenderRepository genderRepository,
                       ExerciseSchemeRepository exerciseSchemeRepository, EquipmentRepository equipmentRepository,
                       UserRepository userRepository, TrainingRepository trainingRepository,
                       ExerciseRepository exerciseRepository, SetSchemeRepository setSchemeRepository,
                       BodyPartRepository bodyPartRepository, RoleRepository roleRepository) {
        this.gymChampionApplication = gymChampionApplication;
        this.genderRepository = genderRepository;
        this.exerciseSchemeRepository = exerciseSchemeRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
        this.setSchemeRepository = setSchemeRepository;
        this.bodyPartRepository = bodyPartRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeExerciseSchemeTableContent(exerciseSchemeRepository);
        initializeGenderTableContent(genderRepository);
        initializeEquipmentTableContent(equipmentRepository);
        initializeBodyPartTableContent(bodyPartRepository);
        initializeExerciseTableContent(exerciseRepository);
        initializeUsers(userRepository);
        initializeTraining(trainingRepository);
        initializeSetSchemeTable(setSchemeRepository);
        initializeRoles(roleRepository);
    }

    private void initializeGenderTableContent(GenderRepository genderRepository){
        genderRepository.save(maleGender);
        genderRepository.save(femaleGender);
    }

    private void initializeEquipmentTableContent(EquipmentRepository equipmentRepository ){
        equipmentRepository.save(barbellEquipment);
        equipmentRepository.save(dumbbellEquipment);
        equipmentRepository.save(machineEquipment);
        equipmentRepository.save(noEquipmentEquipment);
    }

    private void initializeExerciseSchemeTableContent(ExerciseSchemeRepository exerciseSchemeRepository) {
        exerciseSchemeRepository.save(upperBodyExScheme);
        exerciseSchemeRepository.save(lowerBodyExScheme);
    }

    private void initializeBodyPartTableContent(BodyPartRepository bodyPartRepository) {
        bodyPartRepository.save(shouldersBodyPart);
        bodyPartRepository.save(backBodyPart);
        bodyPartRepository.save(chestBodyPart);
        bodyPartRepository.save(quadsBodyPart);
        bodyPartRepository.save(hamstringsBodyPart);
        bodyPartRepository.save(armsBodyPart);
        bodyPartRepository.save(calvesBodyPart);
    }

    private void initializeUsers(UserRepository userRepository) {
        mihuUser = new User("Mihu", "the_mihu", 88.5);
        mihuUser.setBirthDate(new Date(176892927000L));
        mihuUser.setGender(maleGender);
        userRepository.save(mihuUser);

        krzychuUser = new User("Krzychu", "duży_krzychu", 85);
        krzychuUser.setBirthDate(new Date(655721727000L));
        krzychuUser.setGender(maleGender);
        userRepository.save(krzychuUser);

        jodlaUser = new User("Jodla", "scrum_maestro", 75);
        jodlaUser.setBirthDate(new Date(536748927000L));
        jodlaUser.setGender(maleGender);
        userRepository.save(jodlaUser);

        zosiaUser = new User("Zosia", "zocha", 62);
        zosiaUser.setBirthDate(new Date(794566527000L));
        zosiaUser.setGender(femaleGender);
        userRepository.save(zosiaUser);
    }

    private void initializeRoles(RoleRepository roleRepository) {
        user = new Role("USER");
        admin = new Role("ADMIN");
        roleRepository.save(user);
        roleRepository.save(admin);
    }


    private void initializeExerciseTableContent(ExerciseRepository exerciseRepository) {
        barbellBenchPress = new Exercise("barbell_bench_press", 500, 500d);
        barbellBenchPress.setEquipment(barbellEquipment);
        barbellBenchPress.setBodyPart(chestBodyPart);
        barbellBenchPress.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(barbellBenchPress);

        squats = new Exercise("squats", 500, 500d);
        squats.setEquipment(barbellEquipment);
        squats.setBodyPart(quadsBodyPart);
        squats.setExerciseScheme(lowerBodyExScheme);
        exerciseRepository.save(squats);

        militaryPress = new Exercise("military_press", 500, 300d);
        militaryPress.setEquipment(barbellEquipment);
        militaryPress.setBodyPart(shouldersBodyPart);
        militaryPress.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(militaryPress);

        Exercise deadLift = new Exercise("dead_lift", 500, 800d);
        deadLift.setEquipment(barbellEquipment);
        deadLift.setBodyPart(hamstringsBodyPart);
        deadLift.setExerciseScheme(lowerBodyExScheme);
        exerciseRepository.save(deadLift);

        pullUps = new Exercise("pull_ups", 200, 200d);
        pullUps.setEquipment(noEquipmentEquipment);
        pullUps.setBodyPart(backBodyPart);
        pullUps.setExerciseScheme(upperBodyExScheme);
        exerciseRepository.save(pullUps);
    }

    private void initializeTraining(TrainingRepository trainingRepository) {

        tr001 = new Training(false);
        tr001.setTrainingDateStart(new Date(1546512327000L));
        tr001.setTrainingDateFinish(new Date(1546519218000L));
        tr001.setUser(mihuUser);
        trainingRepository.save(tr001);


        tr002 = new Training(false);
        tr002.setTrainingDateStart(new Date(1547124927000L));
        tr002.setTrainingDateFinish(new Date(1547128518000L));
        tr002.setUser(mihuUser);
        trainingRepository.save(tr002);

        tr003 = new Training(false);
        tr003.setTrainingDateStart(new Date(1547300727000L));
        tr003.setTrainingDateFinish(new Date(1547309718000L));
        tr003.setUser(mihuUser);
        trainingRepository.save(tr003);

        tr004 = new Training(false);
        tr004.setTrainingDateStart(new Date(1547991137000L));
        tr004.setTrainingDateFinish(new Date(1547997318000L));
        tr004.setUser(mihuUser);
        trainingRepository.save(tr004);

        tr005 = new Training(false);
        tr005.setTrainingDateStart(new Date(1547289927000L));
        tr005.setTrainingDateFinish(new Date(1547298978000L));
        tr005.setUser(krzychuUser);
        trainingRepository.save(tr005);

        tr006 = new Training(false);
        tr006.setTrainingDateStart(new Date(1547549127000L));
        tr006.setTrainingDateFinish(new Date(1547558178000L));
        tr006.setUser(krzychuUser);
        trainingRepository.save(tr006);

        tr007 = new Training(false);
        tr007.setTrainingDateStart(new Date(1550141177000L));
        tr007.setTrainingDateFinish(new Date(1550146578000L));
        tr007.setUser(krzychuUser);
        trainingRepository.save(tr007);

        Training tr008 = new Training(false);
        tr008.setTrainingDateStart(new Date(1549795527000L));
        tr008.setUser(jodlaUser);
        trainingRepository.save(tr008);

        Training tr009 = new Training(false);
        tr009.setTrainingDateStart(new Date(1547124927000L));
        tr009.setUser(jodlaUser);
        trainingRepository.save(tr009);

        Training tr010 = new Training(false);
        tr010.setTrainingDateStart(new Date(1550141127000L));
        tr010.setUser(jodlaUser);
        trainingRepository.save(tr010);

        Training tr011 = new Training(false);
        tr011.setTrainingDateStart(new Date(1549968327000L));
        tr011.setUser(jodlaUser);
        trainingRepository.save(tr011);

        Training tr012 = new Training(false);
        tr012.setTrainingDateStart(new Date(1547127927000L));
        tr012.setUser(zosiaUser);
        trainingRepository.save(tr012);

        Training tr013 = new Training(false);
        tr013.setTrainingDateStart(new Date(1547981127000L));
        tr013.setUser(zosiaUser);
        trainingRepository.save(tr013);

        Training tr014 = new Training(false);
        tr014.setTrainingDateStart(new Date(1549795527000L));
        tr014.setUser(zosiaUser);
        trainingRepository.save(tr014);
    }

    private void initializeSetSchemeTable(SetSchemeRepository setSchemeRepository) {

        // MIHU training 1
        SetScheme set1Tr1MihuChest = new SetScheme(12, 40);
        set1Tr1MihuChest.setTraining(tr001);
        set1Tr1MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set1Tr1MihuChest);

        SetScheme set2Tr1MihuChest = new SetScheme(8, 60);
        set2Tr1MihuChest.setTraining(tr001);
        set2Tr1MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set2Tr1MihuChest);

        SetScheme set3Tr1MihuChest = new SetScheme(8, 80);
        set3Tr1MihuChest.setTraining(tr001);
        set3Tr1MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set3Tr1MihuChest);

        SetScheme set4Tr1MihuChest = new SetScheme(8, 80);
        set4Tr1MihuChest.setTraining(tr001);
        set4Tr1MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set4Tr1MihuChest);


        // MIHU training 2
        SetScheme set1Tr2MihuBack = new SetScheme(12, 0);
        set1Tr2MihuBack.setTraining(tr002);
        set1Tr2MihuBack.setExercise(pullUps);
        setSchemeRepository.save(set1Tr2MihuBack);

        SetScheme set2Tr2MihuBack = new SetScheme(10, 0);
        set2Tr2MihuBack.setTraining(tr002);
        set2Tr2MihuBack.setExercise(pullUps);
        setSchemeRepository.save(set2Tr2MihuBack);

        SetScheme set1Tr2MihuQuads = new SetScheme(10, 80);
        set1Tr2MihuQuads.setTraining(tr002);
        set1Tr2MihuQuads.setExercise(squats);
        setSchemeRepository.save(set1Tr2MihuQuads);


        // MIHU training 3
        SetScheme set1Tr3MihuChest = new SetScheme(10, 55);
        set1Tr3MihuChest.setTraining(tr003);
        set1Tr3MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set1Tr3MihuChest);

        SetScheme set2Tr3MihuChest = new SetScheme(10, 70);
        set2Tr3MihuChest.setTraining(tr003);
        set2Tr3MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set2Tr3MihuChest);

        SetScheme set3Tr3MihuChest = new SetScheme(9, 85);
        set3Tr3MihuChest.setTraining(tr003);
        set3Tr3MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set3Tr3MihuChest);

        SetScheme set4Tr3MihuChest = new SetScheme(3, 95);
        set4Tr3MihuChest.setTraining(tr003);
        set4Tr3MihuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set4Tr3MihuChest);

        // MIHU training 4
        SetScheme set1Tr4MihuBack = new SetScheme(15, 0);
        set1Tr4MihuBack.setTraining(tr004);
        set1Tr4MihuBack.setExercise(pullUps);
        setSchemeRepository.save(set1Tr4MihuBack);

        SetScheme set2Tr4MihuShoulders = new SetScheme(10, 30);
        set2Tr4MihuShoulders.setTraining(tr004);
        set2Tr4MihuShoulders.setExercise(militaryPress);
        setSchemeRepository.save(set2Tr4MihuShoulders);

        SetScheme set1Tr4MihuQuads = new SetScheme(5, 100);
        set1Tr4MihuQuads.setTraining(tr004);
        set1Tr4MihuQuads.setExercise(squats);
        setSchemeRepository.save(set1Tr4MihuQuads);



        // Krzychu training 1
        SetScheme set1Tr1KrzychuChest = new SetScheme(10, 55);
        set1Tr1KrzychuChest.setTraining(tr005);
        set1Tr1KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set1Tr1KrzychuChest);

        SetScheme set2Tr1KrzychuChest = new SetScheme(8, 90);
        set2Tr1KrzychuChest.setTraining(tr005);
        set2Tr1KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set2Tr1KrzychuChest);

        SetScheme set3Tr1KrzychuChest = new SetScheme(3, 120);
        set3Tr1KrzychuChest.setTraining(tr005);
        set3Tr1KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set3Tr1KrzychuChest);

        SetScheme set4Tr1KrzychuChest = new SetScheme(15, 60);
        set4Tr1KrzychuChest.setTraining(tr005);
        set4Tr1KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set4Tr1KrzychuChest);

        // Krzychu training 2
        SetScheme set1Tr2KrzychuBack = new SetScheme(7, 0);
        set1Tr2KrzychuBack.setTraining(tr006);
        set1Tr2KrzychuBack.setExercise(pullUps);
        setSchemeRepository.save(set1Tr2KrzychuBack);

        SetScheme set2Tr2KrzychuBack = new SetScheme(8, 0);
        set2Tr2KrzychuBack.setTraining(tr006);
        set2Tr2KrzychuBack.setExercise(pullUps);
        setSchemeRepository.save(set2Tr2KrzychuBack);

        SetScheme set1Tr2KrzychuQuads = new SetScheme(10, 120);
        set1Tr2KrzychuQuads.setTraining(tr006);
        set1Tr2KrzychuQuads.setExercise(squats);
        setSchemeRepository.save(set1Tr2KrzychuQuads);

        // Krzychu training 3
        SetScheme set1Tr3KrzychuChest = new SetScheme(10, 70);
        set1Tr3KrzychuChest.setTraining(tr007);
        set1Tr3KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set1Tr3KrzychuChest);

        SetScheme set2Tr3KrzychuChest = new SetScheme(3, 100);
        set2Tr3KrzychuChest.setTraining(tr007);
        set2Tr3KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set2Tr3KrzychuChest);

        SetScheme set3Tr3KrzychuChest = new SetScheme(1, 135);
        set3Tr3KrzychuChest.setTraining(tr007);
        set3Tr3KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set3Tr3KrzychuChest);

        SetScheme set4Tr3KrzychuChest = new SetScheme(5, 95);
        set4Tr3KrzychuChest.setTraining(tr007);
        set4Tr3KrzychuChest.setExercise(barbellBenchPress);
        setSchemeRepository.save(set4Tr3KrzychuChest);

    }

}
