package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private UserRepository userRepository;

    @Autowired
    public TrainingService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }
}
