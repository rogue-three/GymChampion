package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

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
    public List<Training> getAllArchivizedTrainings() {
        return this.trainingService.getAllArchivizedTrainings();
    }

    @GetMapping("/user/{login}")
    public List<Training> getTrainingsByUserLogin(@PathVariable("login") String login) {
        return this.trainingService.getTrainingsByUserLogin(login);
    }

    @GetMapping("count/user/{login}")
    public int countTrainingsByUserLogin(@PathVariable("login") String login) {
        return this.trainingService.countTrainingsByUserLogin(login);
    }
    @PutMapping("/archivize/{id}")
    public Training archivizeUser(@PathVariable("id")int id) {
        return this.trainingService.archivizeTraining(id);
    }


}
