package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.model.SetScheme;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheme")
public class SetSchemeController {

    private SetSchemeService setSchemeService;

    @Autowired
    public SetSchemeController(SetSchemeService setSchemeService) {
        this.setSchemeService = setSchemeService;
    }

    @PostMapping("/all")
    public List<SetScheme> saveSchemeList(@RequestBody List<SetScheme> schemeList) {
        return setSchemeService.addSchemeListToRepository(schemeList);
    }

    @PostMapping
    public SetScheme saveScheme(@RequestBody SetScheme scheme) {
        return setSchemeService.addSchemeToRepository(scheme);
    }

    @GetMapping("/{training_id}")
    public List<SetScheme> getSchemeByTrainingId(@PathVariable("training_id") int trainingId) {
        return  setSchemeService.getSchemeByTrainingId(trainingId);
    }

    @GetMapping("/max/{login}/{exerciseId}")
    public SetScheme  getBestSchemeByExerciseId(@PathVariable("exerciseId") int exerciseId,
                                               @PathVariable("login") String login) {
        return setSchemeService.getMaxSchemeByExercise(exerciseId, login);
    }

}
