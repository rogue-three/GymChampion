package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.ExerciseScheme;
import com.gymchampion.GymChampion.service.ExerciseSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise_scheme")
public class ExerciseSchemeController {

    private ExerciseSchemeService exerciseSchemeService;

    @Autowired
    public ExerciseSchemeController(ExerciseSchemeService exerciseSchemeService) {
        this.exerciseSchemeService = exerciseSchemeService;
    }

    @GetMapping
    public List<ExerciseScheme> getAllExerciseSchemes() {
        return this.exerciseSchemeService.getAllExerciseSchemes();
    }

    @GetMapping("/{id}")
    public ExerciseScheme getExerciseSchemeById(@PathVariable("id") int id) {
        return this.exerciseSchemeService.getExerciseSchemeById(id);
    }

    @PostMapping
    public ExerciseScheme addExerciseScheme(@RequestBody ExerciseScheme exerciseScheme) {
        return this.exerciseSchemeService.addExerciseScheme(exerciseScheme);
    }
}
