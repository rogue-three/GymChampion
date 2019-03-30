package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments() {
        return this.equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(int id) {
        Optional<Equipment> optionalBodypart = this.equipmentRepository.findById(id);
        return optionalBodypart.orElseGet(null);
    }

    public Equipment addEquipment(Equipment equipment) {
        return this.equipmentRepository.save(equipment);
    }

}
