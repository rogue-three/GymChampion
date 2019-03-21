package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}
