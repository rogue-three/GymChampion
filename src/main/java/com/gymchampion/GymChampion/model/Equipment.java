package com.gymchampion.GymChampion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment {

    public Equipment(int equipmentId, String equipmentName) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
    }

    public Equipment() {
    }

    @Id
    @Column(name = "equipment_id")
    private int equipmentId;

    @Column(nullable = false)
    private String equipmentName;

}
