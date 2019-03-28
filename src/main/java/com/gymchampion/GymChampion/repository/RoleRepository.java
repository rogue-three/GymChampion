package com.gymchampion.GymChampion.repository;


import com.gymchampion.GymChampion.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role getByRoleName(String roleName);
}
