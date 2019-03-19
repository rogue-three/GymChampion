package com.gymchampion.GymChampion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "body_part")
public class BodyPart {

    @Id
    @Column(name = "body_part_id")
    private int id;

    @Column(nullable = false)
    private String name;

}
