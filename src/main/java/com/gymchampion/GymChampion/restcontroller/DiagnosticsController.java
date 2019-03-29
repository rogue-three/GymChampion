package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diagnostics")
public class DiagnosticsController {

    private TrainingService trainingService;
    private SetSchemeService setSchemeService;

    @Autowired
    public DiagnosticsController(TrainingService trainingService,
                                 SetSchemeService setSchemeService) {
        this.trainingService = trainingService;
        this.setSchemeService = setSchemeService;
    }

    /*
     *      TODO - 1RM count
     */
}
