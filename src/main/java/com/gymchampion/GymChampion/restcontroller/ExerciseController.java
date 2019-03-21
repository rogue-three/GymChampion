//package com.gymchampion.GymChampion.restcontroller;
//
//
//import com.gymchampion.GymChampion.service.ExerciseService;
//import com.gymchampion.GymChampion.model.Exercise;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/exercise")
//public class ExerciseController {
//
//    private ExerciseService exerciseService;
//
//    @Autowired
//    public ExerciseController(ExerciseService service) {
//        this.exerciseService = service;
//    }
//
//    @GetMapping("/{scheme}")
//    public List<Exercise> getExerciseByScheme(@PathVariable("scheme") String scheme) {
//
//        return exerciseService.getListOfExerciseByScheme(scheme);
//
//    }
//
//
//
//
//
//}
