package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Role;
import com.gymchampion.GymChampion.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByRoleName(String roleName) {
        return this.roleRepository.getByRoleName(roleName);
    }
}
