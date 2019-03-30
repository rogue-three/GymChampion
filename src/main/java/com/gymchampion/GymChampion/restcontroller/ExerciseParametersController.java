package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceAlreadyExistsException;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
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

    @GetMapping("/body_part")
    public ResponseEntity<List<BodyPart>> getAllBodyParts() {
        logger.info("Fetching all Body parts.");
        List<BodyPart> bodyParts = this.bodyPartService.getAllBodyParts();
        if (bodyParts.isEmpty()) {
            logger.error("Body parts not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bodyParts, HttpStatus.OK);
    }

    @GetMapping("/body_part/id/{id}")
    public ResponseEntity<?> getBodyPartById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Body part with id %d.", id));
        BodyPart bodyPart = this.bodyPartService.getBodyPartById(id);
        if (bodyPart == null) {
            logger.error(String.format("Body part with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Body part with id" + id
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bodyPart, HttpStatus.OK);
    }

    @GetMapping("/body_part/name/{name}")
    public ResponseEntity<?> getBodyPartByName(@PathVariable("name") String bodyPartName) {
        logger.info(String.format("Fetching Body part with name %s.", bodyPartName));
        BodyPart bodyPart = this.bodyPartService.getBodyPartByName(bodyPartName);
        if (bodyPart == null) {
            logger.error(String.format("Body part with name %s not found.", bodyPartName));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Body part with name" +
                    bodyPartName + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bodyPart, HttpStatus.OK);
    }

    @PatchMapping("/body_part")
    public ResponseEntity<?> updateBodyPartName(@RequestBody BodyPart bodyPart) {
        logger.info(String.format("Updating Body part with id %d name to %s.",
                bodyPart.getBodyPartId(),
                bodyPart.getBodyPartName()));
        BodyPart bodyPartFromDB = this.bodyPartService.getBodyPartById(bodyPart.getBodyPartId());
        if (bodyPartFromDB == null) {
            logger.error(String.format("Unable to update body part name. Body part with id %d not found.",
                    bodyPart.getBodyPartId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. " +
                    "Body part with id " + bodyPart.getBodyPartId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        bodyPartFromDB.setBodyPartName(bodyPart.getBodyPartName());
        this.bodyPartService.updateBodyPart(bodyPartFromDB);
        return new ResponseEntity<>(bodyPart, HttpStatus.OK);
    }

    @DeleteMapping("/body_part/{id}")
    public ResponseEntity<?> removeBodyPartById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Body part with id %d.", id));
        BodyPart bodyPart = this.bodyPartService.getBodyPartById(id);
        if (bodyPart == null) {
            logger.error(String.format("Unable to delete. Body part with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete." +
                    "Body part with id %d " + id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.bodyPartService.removeBodyPart(bodyPart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @GetMapping("/equipment")
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        logger.info("Fetching all Equipments.");
        List<Equipment> equipments = this.equipmentService.getAllEquipments();
        if (equipments.isEmpty()) {
            logger.error("Equipments not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    @GetMapping("/equipment/id/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Equipment with id %d.", id));
        Equipment equipment = this.equipmentService.getEquipmentById(id);
        if (equipment == null) {
            logger.error(String.format("Equipment with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Equipment with id" + id
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping("/equipment/name/{name}")
    public ResponseEntity<?> getEquipmentByName(@PathVariable("name") String equipmentName) {
        logger.info(String.format("Fetching Equipment with name %s.", equipmentName));
        Equipment equipment = this.equipmentService.getEquipmentByName(equipmentName);
        if (equipment == null) {
            logger.error(String.format("Equipment with name %s not found.", equipmentName));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Equipment with name" +
                    equipmentName + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @PatchMapping("/equipment")
    public ResponseEntity<?> updateEquipmentName(@RequestBody Equipment equipment) {
        logger.info(String.format("Updating Equipment with id %d name to %s.",
                equipment.getEquipmentId(),
                equipment.getEquipmentName()));
        Equipment equipmentFromDB = this.equipmentService.getEquipmentById(equipment.getEquipmentId());
        if (equipmentFromDB == null) {
            logger.error(String.format("Unable to update equipment name. Equipment with id %d not found.",
                    equipment.getEquipmentId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. " +
                    "Equipment with id " + equipment.getEquipmentId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        equipmentFromDB.setEquipmentName(equipment.getEquipmentName());
        this.equipmentService.updateEquipment(equipment);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<?> removeEquipmentById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Equipment with id %d.", id));
        Equipment equipment = this.equipmentService.getEquipmentById(id);
        if (equipment == null) {
            logger.error(String.format("Unable to delete. Equipment with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete." +
                    "Equipment with id %d " + id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.equipmentService.removeEquipment(equipment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @GetMapping("/exercise_scheme")
    public ResponseEntity<List<ExerciseScheme>> getAllExerciseSchemes() {
        logger.info("Fetching all Exercise schemes.");
        List<ExerciseScheme> exerciseSchemes = this.exerciseSchemeService.getAllExerciseSchemes();
        if (exerciseSchemes.isEmpty()) {
            logger.error("Exercise schemes not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exerciseSchemes, HttpStatus.OK);
    }

    @GetMapping("/exercise_scheme/id/{id}")
    public ResponseEntity<?> getExerciseSchemeById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Exercise sxheme with id %d.", id));
        ExerciseScheme exerciseScheme = this.exerciseSchemeService.getExerciseSchemeById(id);
        if (exerciseScheme == null) {
            logger.error(String.format("Exercise scheme with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Exercise scheme with id" + id
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exerciseScheme, HttpStatus.OK);
    }

    @GetMapping("/exercise_scheme/name/{name}")
    public ResponseEntity<?> getExerciseSchemeByName(@PathVariable("name") String exerciseSchemeName) {
        logger.info(String.format("Fetching Exercise scheme with name %s.", exerciseSchemeName));
        ExerciseScheme exerciseScheme = this.exerciseSchemeService.getExerciseSchemeByName(exerciseSchemeName);
        if (exerciseScheme == null) {
            logger.error(String.format("Exercise scheme with name %s not found.", exerciseSchemeName));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Exercise scheme with name" +
                    exerciseSchemeName + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exerciseScheme, HttpStatus.OK);
    }

    @PatchMapping("/exercise_scheme")
    public ResponseEntity<?> updateExerciseSchemeName(@RequestBody ExerciseScheme exerciseScheme) {
        logger.info(String.format("Updating Exercise scheme with id %d name to %s.",
                exerciseScheme.getExerciseSchemeId(),
                exerciseScheme.getExerciseSchemeName()));
        ExerciseScheme exerciseSchemeFromDB = this.exerciseSchemeService.getExerciseSchemeById(exerciseScheme.getExerciseSchemeId());
        if (exerciseSchemeFromDB == null) {
            logger.error(String.format("Unable to update exercise scheme name. Exercise scheme with id %d not found.",
                    exerciseScheme.getExerciseSchemeId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. " +
                    "Exercise scheme with id " + exerciseScheme.getExerciseSchemeId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        exerciseSchemeFromDB.setExerciseSchemeName(exerciseScheme.getExerciseSchemeName());
        this.exerciseSchemeService.updateExerciseScheme(exerciseScheme);
        return new ResponseEntity<>(exerciseScheme, HttpStatus.OK);
    }

    @DeleteMapping("/exercise_scheme/{id}")
    public ResponseEntity<?> removeExerciseSchemeById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Exercise scheme with id %d.", id));
        ExerciseScheme exerciseScheme = this.exerciseSchemeService.getExerciseSchemeById(id);
        if (exerciseScheme == null) {
            logger.error(String.format("Unable to delete. Exercise scheme with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete." +
                    "Exercise scheme with id %d " + id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.exerciseSchemeService.removeExerciseScheme(exerciseScheme);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
