package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
