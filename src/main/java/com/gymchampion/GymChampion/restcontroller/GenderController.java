package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.Gender;
import com.gymchampion.GymChampion.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gender")
public class GenderController {

    private GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping
    public List<Gender> getAllGenders() {
        return this.genderService.getAllGenders();
    }
}
