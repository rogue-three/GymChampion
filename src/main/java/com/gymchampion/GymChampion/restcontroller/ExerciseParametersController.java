package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceAlreadyExistsException;
import com.gymchampion.GymChampion.model.BodyPart;
import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.model.ExerciseScheme;
import com.gymchampion.GymChampion.service.BodyPartService;
import com.gymchampion.GymChampion.service.EquipmentService;
import com.gymchampion.GymChampion.service.ExerciseSchemeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping("/body_part")
    public ResponseEntity<?> addBodyPart(@RequestBody BodyPart bodyPart, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating Body part with name %s.", bodyPart.getBodyPartName()));
        if (this.bodyPartService.doesBodyPartExist(bodyPart)) {
            logger.error(String.format("Unable to create. Body part with name %s already exist.", bodyPart.getBodyPartName()));
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. Body part with name " +
                    bodyPart.getBodyPartName() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.bodyPartService.addBodyPart(bodyPart);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/exercise_parameters/body_part/{id}").
                buildAndExpand(bodyPart.getBodyPartId()).toUri());
        logger.info("Body part created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // getall

    // getbyid

    //getbyname

    //update name

    //remove


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

    @PostMapping("/equipment")
    public ResponseEntity<?> addEquipment(@RequestBody Equipment equipment, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating Equipment with name %s.", equipment.getEquipmentName()));
        if (this.equipmentService.doesEquipmentExists(equipment)) {
            logger.error(String.format("Unable to create. Equipment with name %s already exist.", equipment.getEquipmentName()));
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. Equipment with name " +
                    equipment.getEquipmentName() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.equipmentService.addEquipment(equipment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/exercise_parameters/equipment/{id}").
                buildAndExpand(equipment.getEquipmentId()).toUri());
        logger.info("Equipment created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // getall

    // getbyid

    //getbyname

    //update name

    //remove

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

    @PostMapping("/exercise_scheme")
    public ResponseEntity<?> addExerciseScheme(@RequestBody ExerciseScheme exerciseScheme, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating Exercise scheme with name %s.", exerciseScheme.getExerciseSchemeName()));
        if (this.exerciseSchemeService.doesExerciseSchemeExist(exerciseScheme)) {
            logger.error(String.format("Unable to create. Exercise schme with name %s already exist.", exerciseScheme.getExerciseSchemeName()));
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. Exercise scheme with name " +
                    exerciseScheme.getExerciseSchemeName() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.exerciseSchemeService.addExerciseScheme(exerciseScheme);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/exercise_parameters/exercise_scheme/{id}").
                buildAndExpand(exerciseScheme.getExerciseSchemeId()).toUri());
        logger.info("Exercise scheme created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // getall

    // getbyid

    //getbyname

    //update name

    //remove

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
