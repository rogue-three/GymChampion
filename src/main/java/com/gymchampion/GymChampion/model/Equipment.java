package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @Column(name = "equipmnent_id")
    @GeneratedValue
    private int equipmentId;

    @Column(name = "equipment_name", length = 30, nullable = false)
    private String equipmentName;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    public Equipment() {}

    public Equipment(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquipmentId() {
        return this.equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return this.equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public List<Exercise> getExercises() { return this.exercises; }

    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}
