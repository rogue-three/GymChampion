package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session findBySessionKey(String key);

    List<Session> findAllByActive(boolean isActive);
}
