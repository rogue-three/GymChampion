package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.model.BodyPart;
import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.model.ExerciseScheme;
import com.gymchampion.GymChampion.service.BodyPartService;
import com.gymchampion.GymChampion.service.EquipmentService;
import com.gymchampion.GymChampion.service.ExerciseSchemeService;
import com.gymchampion.GymChampion.service.ExerciseService;
import com.gymchampion.GymChampion.model.Exercise;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;
    private BodyPartService bodyPartService;
    private EquipmentService equipmentService;
    private ExerciseSchemeService exerciseSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public ExerciseController(ExerciseService service,
                              BodyPartService bodyPartService,
                              EquipmentService equipmentService,
                              ExerciseSchemeService exerciseSchemeService) {
        this.exerciseService = service;
        this.bodyPartService = bodyPartService;
        this.equipmentService = equipmentService;
        this.exerciseSchemeService = exerciseSchemeService;
    }

    /*
     *   Exercise handling
     */

    @GetMapping
    public List<Exercise> getAllExercises() {
        return this.exerciseService.getAllExercises();
    }

    @GetMapping("/id/{id}")
    public Exercise getExerciseById(@PathVariable int id) {
        return this.exerciseService.getExerciseById(id);
    }

    @GetMapping("/scheme/{scheme}")
    public List<Exercise> getExerciseByScheme(@PathVariable("scheme") String scheme) {
        return this.exerciseService.getListOfExerciseByScheme(scheme);
    }

    @GetMapping("/bodypart/{bodyPart}")
    public List<Exercise> getExercisesByBodyPart(@PathVariable("bodyPart") String bodyPart) {
        return this.exerciseService.getAllExerciseByBodyPart(bodyPart);
    }

    @GetMapping("equipment/{equipment}")
    public List<Exercise> getExercisesByEquipment(@PathVariable("equipment") String equipment) {
        return this.exerciseService.getAllExerciseByEquipment(equipment);
    }

    @PostMapping
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return this.exerciseService.addExercise(exercise);
    }

    @PutMapping("/max_reps/admin")
    public Exercise setExerciseMaxReps(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseMaxReps(exercise);
    }

    @PutMapping("/max_weight/admin")
    public Exercise setExerciseMaxWeight(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseMaxWeight(exercise);
    }

    @PutMapping("/equipment/admin")
    public Exercise setExerciseEquipment(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseEquipment(exercise);
    }

    /*
     *   BodyPart handling
     */


    @GetMapping("/body_part")
    public List<BodyPart> getAllBodyParts() {
        return this.bodyPartService.getAllBodyParts();
    }

    @GetMapping("/body_part/{id}")
    public BodyPart getBodyPartById(@PathVariable("id") int id) {
        return this.bodyPartService.getBodyPartById(id);
    }

    @PostMapping("/body_part/admin")
    public BodyPart addBodyPart(@RequestBody BodyPart bodyPart) {
        return this.bodyPartService.addBodyPart(bodyPart);
    }

    /*
     *   Equipment handling
     */

    @GetMapping("/equipment")
    public List<Equipment> getAllEquipments() {
        return this.equipmentService.getAllEquipments();
    }

    @GetMapping("/equipment/{id}")
    public Equipment getEquipmentById(@PathVariable("id") int id) {
        return this.equipmentService.getEquipmentById(id);
    }

    @PostMapping("/equipment/admin")
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        return this.equipmentService.addEquipment(equipment);
    }

    /*
     *   ExerciseScheme handling
     */

    @GetMapping("/exercise_scheme")
    public List<ExerciseScheme> getAllExerciseSchemes() {
        return this.exerciseSchemeService.getAllExerciseSchemes();
    }

    @GetMapping("/exercise_scheme/{id}")
    public ExerciseScheme getExerciseSchemeById(@PathVariable("id") int id) {
        return this.exerciseSchemeService.getExerciseSchemeById(id);
    }

    @PostMapping("/exercise_scheme/admin")
    public ExerciseScheme addExerciseScheme(@RequestBody ExerciseScheme exerciseScheme) {
        return this.exerciseSchemeService.addExerciseScheme(exerciseScheme);
    }
}
