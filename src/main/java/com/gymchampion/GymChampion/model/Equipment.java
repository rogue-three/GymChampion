package com.gymchampion.GymChampion.model;


import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @Column(name = "equipmnent_id")
    @GeneratedValue
    private int equipmentId;

    @Column(name = "equipment_name", length = 50, nullable = false)
    private String equipmentName;

    public Equipment() {
    }

    public Equipment(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}