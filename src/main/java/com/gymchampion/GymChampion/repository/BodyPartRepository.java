package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyPartRepository extends JpaRepository<BodyPart, Integer> {

    BodyPart findByBodyPartName(String name);
}
