package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.model.BodyPart;
import com.gymchampion.GymChampion.service.BodyPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/body_part")
public class BodyPartController {

    private BodyPartService bodyPartService;

    @Autowired
    public BodyPartController(BodyPartService bodyPartService) {
        this.bodyPartService = bodyPartService;
    }

    @GetMapping
    public List<BodyPart> getAllBodyParts() {
        return this.bodyPartService.getAllBodyParts();
    }

    @GetMapping("/{id}")
    public BodyPart getBodyPartById(@PathVariable("id") int id) {
        return this.bodyPartService.getBodyPartById(id);
    }

    @PostMapping
    public BodyPart addBodyPart(@RequestBody BodyPart bodyPart) {
        return this.bodyPartService.addBodyPart(bodyPart);
    }
}
