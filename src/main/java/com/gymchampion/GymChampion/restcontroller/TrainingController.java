package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {

    private TrainingService trainingService;
    private SetSchemeService setSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public TrainingController(TrainingService trainingService,
                              SetSchemeService setSchemeService) {
        this.trainingService = trainingService;
        this.setSchemeService = setSchemeService;
    }

    /*
     *   Training handling
     */

    @PostMapping
    public ResponseEntity<?> addTraining(@RequestBody Training training, UriComponentsBuilder ucBuilder) {
        logger.info(String.format("Creating Training with date %s for user %s.",
                training.getTrainingDateStart(),
                training.getUser().getLogin()));
        this.trainingService.addTraining(training);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/trainings/{id}").buildAndExpand(training.getTrainingId()).toUri());
        logger.info("Training created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings() {
        logger.info("Fetching all Trainings.");
        List<Training> trainings = this.trainingService.getAllTrainings();
        if (trainings.isEmpty()) {
            logger.error("Trainings not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainingById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Training with id %d.", id));
        Training training = this.trainingService.getTrainingById(id);
        if (training == null) {
            logger.error(String.format("Training with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Training with id" + id
                    + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(training, HttpStatus.OK);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<List<Training>> getTrainingsByUserLogin(@PathVariable("login") String login) {
        logger.info(String.format("Fetching Trainings for user with login %s.", login));
        List<Training> trainings = this.trainingService.getTrainingsByUserLogin(login);
        if (trainings.isEmpty()) {
            logger.error(String.format("Trainings for user with login %s not found.", login));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Training>> getTrainingsFromActiveUsers() {
        logger.info("Fetching Trainings for active users.");
        List<Training> trainings = this.trainingService.getAllTrainingsFromActiveUsers();
        if (trainings.isEmpty()) {
            logger.error("Trainings for active users not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping("/active/{login}")
    public ResponseEntity<List<Training>> getNotRemovedTrainingsFromUserByLogin(@PathVariable("login") String login) {
        logger.info(String.format("Fetching not removed Trainings for user %s.", login));
        List<Training> trainings = this.trainingService.getNotRemovedTrainingsFromUserByLogin(login);
        if (trainings.isEmpty()) {
            logger.error(String.format("Not removed Trainings for user %s not found.", login));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<Training>> getArchivedTrainings() {
        logger.info("Fetching archived Trainings.");
        List<Training> trainings = this.trainingService.getAllArchivedTrainings();
        if (trainings.isEmpty()) {
            logger.error("Archived Trainings not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }


    @PatchMapping("/archive/{id}")
    public ResponseEntity<?> archiveTrainingsById(@PathVariable("id") int id) {
        logger.info(String.format("Archiving Training with id %d.", id));
        Training training = this.trainingService.getTrainingById(id);
        if (training == null) {
            logger.error(String.format("Unable to archive. Training with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. Training data with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        training.setArchived(true);
        this.trainingService.updateTraining(training);
        return new ResponseEntity<>(training, HttpStatus.OK);
    }

    @PatchMapping("/archive/login/{login}")
    public ResponseEntity<?> archiveTrainingsByUserLogin(@PathVariable("login") String login) {
        logger.info(String.format("Archiving Trainings for user with login %s.", login));
        List<Training> trainings = this.trainingService.getTrainingsByUserLogin(login);
        if (trainings == null) {
            logger.error(String.format("Unable to archive. Trainings for user with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. " +
                    "Trainings for user with login " + login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        trainings.forEach(training -> training.setArchived(true));
        this.trainingService.updateTrainings(trainings);
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTrainingById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Training with id %d.", id));
        Training training = this.trainingService.getTrainingById(id);
        if (training == null) {
            logger.error(String.format("Unable to delete. Training with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. Training with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.trainingService.removeTraining(training);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/login/{login}")
    public ResponseEntity<?> removeTrainingByUserLogin(@PathVariable("login") String login) {
        logger.info(String.format("Fetching & Deleting Training for user with login %s.", login));
        List<Training> trainings = this.trainingService.getTrainingsByUserLogin(login);
        if (trainings.isEmpty()) {
            logger.error(String.format("Unable to delete. Trainings for user with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete." +
                    "Trainings for user with login %s " + login + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.trainingService.removeTrainings(trainings);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     *   SetScheme handling
     */

    @PostMapping("/set_scheme")
    public ResponseEntity<?> addSetScheme(@RequestBody SetScheme setScheme, UriComponentsBuilder ucBuilder) {
        int setSchemeTrainingId = setScheme.getTraining().getTrainingId();
        logger.info(String.format("Creating SetScheme for Training with id: %d.", setSchemeTrainingId));
        if (this.trainingService.getTrainingById(setSchemeTrainingId) == null) {
            logger.error(String.format("Unable to create. Training with id %d does not exist.", setSchemeTrainingId));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to create. Training with id " +
                    setSchemeTrainingId + " does not exist.").getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        this.setSchemeService.addSchemeToRepository(setScheme);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/training/set_scheme/{id}").buildAndExpand(setScheme.getSetSchemeId()).toUri());
        logger.info("Set scheme created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/set_schemes")
    public ResponseEntity<?> addSetSchemes(@RequestBody List<SetScheme> setSchemes, UriComponentsBuilder ucBuilder) {
        List<Integer> setSchemeTrainingIds = new ArrayList<>();
        setSchemes.forEach(setScheme -> setSchemeTrainingIds.add(setScheme.getTraining().getTrainingId()));
        logger.info("Creating SetSchemes.");
        if (setSchemeTrainingIds.contains(null)) {
            logger.error(String.format("Unable to create. Training with id %d does not exist.", setSchemeTrainingIds.indexOf(null)));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to create. Training with id " +
                    setSchemeTrainingIds.indexOf(null) + " does not exist.").getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        this.setSchemeService.addSchemeListToRepository(setSchemes);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/training/set_schemes/").build().toUri());
        logger.info("Set schemes created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/set_schemes")
    public ResponseEntity<List<SetScheme>> getAllSetSchemes() {
        logger.info("Fetching all Set schemes.");
        List<SetScheme> setSchemes = this.setSchemeService.getAllSetSchemes();
        if (setSchemes.isEmpty()) {
            logger.error("Set schemes not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setSchemes, HttpStatus.OK);
    }

    @GetMapping("/set_schemes/id/{id}")
    public ResponseEntity<?> getSetSchemeById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Set scheme with id %d.", id));
        SetScheme setScheme = this.setSchemeService.getSetSchemeById(id);
        if (setScheme == null) {
            logger.error(String.format("Set scheme with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Set scheme with id " +
                    id + " not found.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setScheme, HttpStatus.OK);
    }

    @GetMapping("/set_schemes/training/{id}")
    public ResponseEntity<List<SetScheme>> getSetSchemesByTrainingId(@PathVariable("id") int id) {
        logger.info(String.format("Fetching Set schems for training id %d.", id));
        List<SetScheme> setSchemes = this.setSchemeService.getSchemesByTrainingId(id);
        if (setSchemes.isEmpty()) {
            logger.error(String.format("Set schemes for training id %d not found.", id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setSchemes, HttpStatus.OK);
    }

    @DeleteMapping("/set_schemes/id/{id}")
    public ResponseEntity<?> removeSetSchemeById(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Set scheme with id %d.", id));
        SetScheme setScheme = this.setSchemeService.getSetSchemeById(id);
        if (setScheme == null) {
            logger.error(String.format("Unable to delete. Set scheme with id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. Set scheme with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.setSchemeService.removeSetScheme(setScheme);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/set_schemes/training/{id}")
    public ResponseEntity<?> removeSetSchemesByTrainingId(@PathVariable("id") int id) {
        logger.info(String.format("Fetching & Deleting Set schemes with training id %d.", id));
        List<SetScheme> setSchemes = this.setSchemeService.getSchemesByTrainingId(id);
        if (setSchemes.isEmpty()) {
            logger.error(String.format("Unable to delete. Set schemes with training id %d not found.", id));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete." +
                    "Set schemes with training id " + id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.setSchemeService.removeSetSchemes(setSchemes);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
