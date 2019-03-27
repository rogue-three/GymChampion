package com.gymchampion.GymChampion.restcontroller;


import com.gymchampion.GymChampion.service.ExerciseService;
import com.gymchampion.GymChampion.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService service) {
        this.exerciseService = service;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return this.exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable int id) {
        return this.exerciseService.getExerciseById(id);
    }

    @PostMapping
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return this.exerciseService.addExercise(exercise);
    }

    @PutMapping("/max_reps/admin")
    public Exercise setExerciseMaxReps(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseMaxReps(exercise);
    }

    @PutMapping("/max_weight/admin")
    public Exercise setExerciseMaxWeight(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseMaxWeight(exercise);
    }

    @PutMapping("/equipment/admin")
    public Exercise setExerciseEquipment(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseEquipment(exercise);
    }

    @GetMapping("/{scheme}")
    public List<Exercise> getExerciseByScheme(@PathVariable("scheme") String scheme) {
        return this.exerciseService.getListOfExerciseByScheme(scheme);
    }
}
