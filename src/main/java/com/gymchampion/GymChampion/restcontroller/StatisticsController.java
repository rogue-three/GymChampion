package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import com.gymchampion.GymChampion.util.TrainingDaysCount;
import com.gymchampion.GymChampion.util.TrainingDurationCount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {

    private TrainingService trainingService;
    private SetSchemeService setSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public StatisticsController(TrainingService trainingService,
                                 SetSchemeService setSchemeService) {
        this.trainingService = trainingService;
        this.setSchemeService = setSchemeService;
    }

    @GetMapping("/max_weight_pulled/{exerciseId}/login/{login}")
    public ResponseEntity<?> getSetSchemeWithMaxWeightByExerciseId(@PathVariable("exerciseId") int exerciseId,
                                                                   @PathVariable("login") String login) {
        logger.info(String.format("Fetching Set scheme with max weight by exercise with id %d", exerciseId));
        SetScheme setScheme = this.setSchemeService.getSetSchemeWithMaxWeightByExerciseId(exerciseId, login);
        if (setScheme == null) {
            logger.error(String.format("Set scheme with max weight by exercise with id %d not found.", exerciseId));
            return new ResponseEntity<>(new ResourceDoesNotExistException(
                    "Set scheme with max weight by exercise with id " +
                    exerciseId + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setScheme, HttpStatus.OK);
    }

    @GetMapping("/training_days/{login}")
    public ResponseEntity<?> countTrainingsByUserLogin(@PathVariable("login") String login) {
        logger.info(String.format("Fetching count of trainings for user with login %s.", login));
        TrainingDaysCount trainingDaysCount = this.trainingService.countTrainingsByUserLogin(login);
        if (trainingDaysCount == null) {
            logger.error(String.format("Trainings for user with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("User with login " +
                    login + "have trained 0 days.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingDaysCount, HttpStatus.OK);
    }

    @GetMapping("/training_duration/{trainingId}")
    public ResponseEntity<?> getTrainingDurationInMillisecondsByTrainingId(@PathVariable("trainingId") int trainingId) {
        logger.info(String.format("Fetching duration of training no. %d", trainingId));
        TrainingDurationCount trainingDurationCount =
                this.trainingService.countTrainingDurationInMillisecondsByTrainingID(trainingId);
        if (trainingDurationCount == null) {
            logger.error(String.format("Training no. %d not found", trainingId));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Training no. " +
                    trainingId + " doesn't exist").getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(trainingDurationCount, HttpStatus.OK);
    }


}
