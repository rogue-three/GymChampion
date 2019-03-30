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
import com.gymchampion.GymChampion.service.ExerciseService;
import com.gymchampion.GymChampion.model.Exercise;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;
    private BodyPartService bodyPartService;
    private EquipmentService equipmentService;
    private ExerciseSchemeService exerciseSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public ExerciseController(ExerciseService exerciseService,
                              BodyPartService bodyPartService,
                              EquipmentService equipmentService,
                              ExerciseSchemeService exerciseSchemeService) {
        this.exerciseService = exerciseService;
        this.bodyPartService = bodyPartService;
        this.equipmentService = equipmentService;
        this.exerciseSchemeService = exerciseSchemeService;
    }

    @PostMapping
    public ResponseEntity<?> addExercise(@RequestBody Exercise exercise, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating Exercise with name %s.", exercise.getExerciseName()));
        if (this.exerciseService.doesExerciseExists(exercise)) {
            logger.error(String.format("Unable to create. Exercise with name %s already exist.", exercise.getExerciseName()));
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. Exercise with name " +
                    exercise.getExerciseName() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.exerciseService.addExercise(exercise);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/exercises/id/{id}").buildAndExpand(exercise.getExerciseId()).toUri());
        logger.info("Exercise created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        logger.info("Fetching all Exercises.");
        List<Exercise> exercises = this.exerciseService.getAllExercises();
        if (exercises.isEmpty()) {
            logger.error("Exercises not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Exercise with id %d.", id));
        Exercise exercise = this.exerciseService.getExerciseById(id);
        if (exercise == null) {
            logger.error(String.format("Exercise with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Exercise with id" + id
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getExerciseByExerciseName(@PathVariable("name") String name) {
        logger.info(String.format("Fetching Exercise with name %s.", name));
        Exercise exercise = this.exerciseService.getExerciseByName(name);
        if (exercise == null) {
            logger.error(String.format("Exercise with name %s not found.", name));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Exercise with name" +
                   name + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @GetMapping("/body_part/{body_part}")
    public ResponseEntity<List<Exercise>> getExercisesByBodyPart(@PathVariable("body_part") String bodyPartName) {
        logger.info(String.format("Fetching Exercises with Body part %s.", bodyPartName));
        List<Exercise> exercises = this.exerciseService.getAllExerciseByBodyPart(bodyPartName);
        if (exercises.isEmpty()) {
            logger.error(String.format("Exercises with Body part %s not found.", bodyPartName));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/equipment/{equipment}")
    public ResponseEntity<List<Exercise>> getExercisesByEquipment(@PathVariable("equipment") String equipmentName) {
        logger.info(String.format("Fetching Exercises for %s equipment.", equipmentName));
        List<Exercise> exercises = this.exerciseService.getAllExerciseByEquipment(equipmentName);
        if (exercises.isEmpty()) {
            logger.error(String.format("Exercises for %s equipment not found.", equipmentName));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/exercise_scheme/{exercise_scheme}")
    public ResponseEntity<List<Exercise>> getExercisesByExerciseScheme(@PathVariable("exercise_scheme") String exerciseSchemeName) {
        logger.info(String.format("Fetching Exercises from %s exercise scheme.", exerciseSchemeName));
        List<Exercise> exercises = this.exerciseService.getListOfExerciseByScheme(exerciseSchemeName);
        if (exercises.isEmpty()) {
            logger.error(String.format("Exercises from %s exercise scheme not found.", exerciseSchemeName));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @PatchMapping("/body_part/{id}")
    public ResponseEntity<?> setExerciseBodyPart(@RequestBody BodyPart bodyPart,
                                                 @PathVariable("id") int id) {
        logger.info(String.format("Setting Exercise with id %d body part to to %s.", id, bodyPart.getBodyPartName()));
        BodyPart bodyPartFromDB = this.bodyPartService.getBodyPartById(bodyPart.getBodyPartId());
        if (bodyPartFromDB == null) {
            logger.error(String.format("Unable to set body part. Body part with id %d not found.", bodyPart.getBodyPartId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set body part. Body part with id " +
                    bodyPart.getBodyPartId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        Exercise exercise = this.exerciseService.getExerciseById(id);
        if (exercise == null) {
            logger.error(String.format("Unable to set body part. Exercise with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set body part. Exercise with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        exercise.setBodyPart(bodyPart);
        this.exerciseService.updateExercise(exercise);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @PatchMapping("/equipment/{id}")
    public ResponseEntity<?> setExerciseEquipment(@RequestBody Equipment equipment,
                                                  @PathVariable("id") int id) {
        logger.info(String.format("Setting Exercise with id %d equipment to %s.", id, equipment.getEquipmentName()));
        Equipment equipmentFromDB = this.equipmentService.getEquipmentById(equipment.getEquipmentId());
        if (equipmentFromDB == null) {
            logger.error(String.format("Unable to set equipment. Equipment with id %d not found.", equipment.getEquipmentId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set equipment. Equipment with id " +
                    equipment.getEquipmentId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        Exercise exercise = this.exerciseService.getExerciseById(id);
        if (exercise == null) {
            logger.error(String.format("Unable to set equipment. Exercise with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set equipment. Exercise with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        exercise.setEquipment(equipment);
        this.exerciseService.updateExercise(exercise);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @PatchMapping("/exercise_scheme/{id}")
    public ResponseEntity<?> setExerciseExerciseScheme(@RequestBody ExerciseScheme exerciseScheme,
                                                       @PathVariable("id") int id) {
        logger.info(String.format("Setting Exercise with id %d exercise scheme to to %s.", id, exerciseScheme.getExerciseSchemeName()));
        ExerciseScheme exerciseSchemeFromDB = this.exerciseSchemeService.getExerciseSchemeById(exerciseScheme.getExerciseSchemeId());
        if (exerciseSchemeFromDB == null) {
            logger.error(String.format("Unable to set exercise scheme. Exercise scheme with id %d not found.", exerciseScheme.getExerciseSchemeId()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set exercise scheme. Exercise scheme with id " +
                    exerciseScheme.getExerciseSchemeId() + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        Exercise exercise = this.exerciseService.getExerciseById(id);
        if (exercise == null) {
            logger.error(String.format("Unable to set exercise scheme. Exercise with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to set exercise scheme. Exercise with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        exercise.setExerciseScheme(exerciseScheme);
        this.exerciseService.updateExercise(exercise);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeExercise(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Exercise with id %d.", id));
        Exercise exercise = this.exerciseService.getExerciseById(id);
        if (exercise == null) {
            logger.error(String.format("Unable to delete. Exercise with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. Exercise with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.exerciseService.removeExerciseById(id);
        return new ResponseEntity<Exercise>(HttpStatus.NO_CONTENT);
    }
}
