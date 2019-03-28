package com.gymchampion.GymChampion.restcontroller;


import com.gymchampion.GymChampion.service.ExerciseService;
import com.gymchampion.GymChampion.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
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

    @GetMapping("/id/{id}")
    public Exercise getExerciseById(@PathVariable int id) {
        return this.exerciseService.getExerciseById(id);
    }

    @GetMapping("/scheme/{scheme}")
    public List<Exercise> getExerciseByScheme(@PathVariable("scheme") String scheme) {
        return this.exerciseService.getListOfExerciseByScheme(scheme);
    }

    @GetMapping("/bodypart/{bodyPart}")
    public List<Exercise> getExercisesByBodyPart(@PathVariable("bodyPart") String bodyPart) {
        return this.exerciseService.getAllExerciseByBodyPart(bodyPart);
    }

    @GetMapping("equipment/{equipment}")
    public List<Exercise> getExercisesByEquipment(@PathVariable("equipment") String equipment) {
        return this.exerciseService.getAllExerciseByEquipment(equipment);
    }

    @PostMapping
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return this.exerciseService.addExercise(exercise);
    }

    @PutMapping("/max_reps")
    public Exercise setExerciseMaxReps(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseMaxReps(exercise);
    }

    @PutMapping("/max_weight")
    public Exercise setExerciseMaxWeight(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseMaxWeight(exercise);
    }

    @PutMapping("/equipment")
    public Exercise setExerciseEquipment(@RequestBody Exercise exercise) {
        return this.exerciseService.setExerciseEquipment(exercise);
    }


}
