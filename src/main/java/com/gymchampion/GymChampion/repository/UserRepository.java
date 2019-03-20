package com.gymchampion.GymChampion.repository;

import com.gymchampion.GymChampion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
