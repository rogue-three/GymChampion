package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diagnostics")
public class DiagnosticsController {

    private TrainingService trainingService;
    private SetSchemeService setSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public DiagnosticsController(TrainingService trainingService,
                                 SetSchemeService setSchemeService) {
        this.trainingService = trainingService;
        this.setSchemeService = setSchemeService;
    }

    @GetMapping("1rmax_expected/{trainingId}/login/{userLogin}")
    public ResponseEntity<?> get1RMaxExpectedByTrainingIdAndUserLogin(@PathVariable("trainingId") int trainingId,
                                                                      @PathVariable("userLogin") String userLogin) {
        logger.info(String.format(
                "Fetching expected 1 Repetition Max in exercise : %d  for user : %s ", trainingId, userLogin));

        SetScheme bestSetScheme =
                this.setSchemeService.getSetSchemeWithHighestExpected1RmaxByExerciseIdAndUserLogin(
                        trainingId, userLogin);

        if(bestSetScheme == null) {
            logger.error("No training was done with exercise no : " + trainingId + "by this user");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Double predictedDoubleCounter = bestSetScheme.getWeight() * (1 + (double) bestSetScheme.getReps()/30);

        return new ResponseEntity<>(predictedDoubleCounter, HttpStatus.OK);
    }



}
