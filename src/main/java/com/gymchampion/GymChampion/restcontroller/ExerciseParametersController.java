package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.model.BodyPart;
import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.model.ExerciseScheme;
import com.gymchampion.GymChampion.service.BodyPartService;
import com.gymchampion.GymChampion.service.EquipmentService;
import com.gymchampion.GymChampion.service.ExerciseSchemeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/exercise_parameters")
public class ExerciseParametersController {

    private BodyPartService bodyPartService;
    private EquipmentService equipmentService;
    private ExerciseSchemeService exerciseSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public ExerciseParametersController(BodyPartService bodyPartService,
                                        EquipmentService equipmentService,
                                        ExerciseSchemeService exerciseSchemeService) {
        this.bodyPartService = bodyPartService;
        this.equipmentService = equipmentService;
        this.exerciseSchemeService = exerciseSchemeService;
    }

    /*
     *   BodyPart handling
     */

    // add bodypart, getallbodyparts, getbodypartbyid, getbodypartbyname,
    // updatebodypartname, removebodypart


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
