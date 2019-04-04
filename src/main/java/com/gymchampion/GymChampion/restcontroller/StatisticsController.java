package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import com.gymchampion.GymChampion.util.IntegerCounter;
import com.gymchampion.GymChampion.util.LongCounter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        IntegerCounter integerCounter = this.trainingService.countTrainingsByUserLogin(login);
        if (integerCounter == null) {
            logger.error(String.format("Trainings for user with login %s not found.", login));
            return new ResponseEntity<>(new ResourceDoesNotExistException("User with login " +
                    login + "have trained 0 days.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(integerCounter, HttpStatus.OK);
    }

    @GetMapping("/training_duration/{trainingId}")
    public ResponseEntity<?> getTrainingDurationInMillisecondsByTrainingId(@PathVariable("trainingId") int trainingId) {
        logger.info(String.format("Fetching duration of training no. %d", trainingId));
        LongCounter longCounter =
                this.trainingService.countTrainingDurationInMillisecondsByTrainingID(trainingId);
        if (longCounter == null) {
            logger.error(String.format("Training no. %d not found", trainingId));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Training no. " +
                    trainingId + " doesn't exist").getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(longCounter, HttpStatus.OK);
    }

    @GetMapping("/list_all_exercise_sets/{exerciseId}/login/{userLogin}")
    public ResponseEntity<?> getAllSetsOfExerciseByUserLogin(@PathVariable("exerciseId") int exerciseId,
                                                             @PathVariable("userLogin") String userLogin){
        logger.info(String.format("Fetching all sets of : " + exerciseId + " of user: " + userLogin + " !"));
        List<SetScheme> allExercisesByExcersiseId =
                this.setSchemeService.getSetSchemeByExerciesIdAndUserLogin(exerciseId, userLogin);
        if(allExercisesByExcersiseId.isEmpty()) {
            logger.error((String.format("There are no exercises for user: " + userLogin)));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allExercisesByExcersiseId, HttpStatus.OK);
    }


}
