package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
