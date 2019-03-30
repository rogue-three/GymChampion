package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.LoginData;
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
                training.getTrainingDate(),
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
        if (trainings == null) {
            logger.error(String.format("Unable to delete. Trainings for user with login %s not found.", id));
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

    // addsetscheme, getall, getsetschemebyid, getall, getsetschemebytrainingid, removesetscheme

    @GetMapping("/set_scheme/{training_id}")
    public List<SetScheme> getSchemeByTrainingId(@PathVariable("training_id") int trainingId) {
        return this.setSchemeService.getSchemeByTrainingId(trainingId);
    }

    @PostMapping("/set_scheme/all")
    public List<SetScheme> saveSchemeList(@RequestBody List<SetScheme> schemeList) {
        return this.setSchemeService.addSchemeListToRepository(schemeList);
    }

    @PostMapping("/set_scheme")
    public SetScheme saveScheme(@RequestBody SetScheme scheme) {
        return this.setSchemeService.addSchemeToRepository(scheme);
    }
}
