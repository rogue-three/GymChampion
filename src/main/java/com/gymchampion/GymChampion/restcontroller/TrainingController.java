package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/training")
public class TrainingController {

    private TrainingService trainingService;
    private SetSchemeService setSchemeService;

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
    public Training addTraining(@RequestBody Training training) {
        return this.trainingService.addTraining(training);
    }

    @GetMapping
    public List<Training> getAllTrainings() {
        return this.trainingService.getAllTrainings();
    }

    @GetMapping("/{id}")
    public Training getTraining(@PathVariable("id") int id) {
        return this.trainingService.getTraining(id);
    }

    @GetMapping("/active")
    public List<Training> getAllTrainingsFromActiveUsers() {
        return this.trainingService.getAllTrainingsFromActiveUsers();
    }

    @GetMapping("/archive")
    public List<Training> getAllArchivedTrainings() {
        return this.trainingService.getAllArchivedTrainings();
    }

    @GetMapping("/user/{login}")
    public List<Training> getTrainingsByUserLogin(@PathVariable("login") String login) {
        return this.trainingService.getTrainingsByUserLogin(login);
    }

    @PutMapping("/archive/{id}")
    public Training archiveUser(@PathVariable("id")int id) {
        return this.trainingService.archiveTraining(id);
    }

    /*
     *   SetScheme handling
     */

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
