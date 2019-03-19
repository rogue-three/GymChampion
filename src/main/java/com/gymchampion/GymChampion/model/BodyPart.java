package com.gymchampion.GymChampion.model;

import javax.persistence.*;


@Entity
@Table(name = "body_part")
public class BodyPart {

    @Id
    @GeneratedValue
    private int bodyPartId;

    @Column(name = "body_part_name", nullable = false)
    private String bodyPartName;

    public BodyPart() {
    }

    public BodyPart(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public int getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(int bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public String getBodyPartName() {
        return bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }
}
