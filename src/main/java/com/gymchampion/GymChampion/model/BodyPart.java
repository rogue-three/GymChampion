package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name = "body_part")
public class BodyPart {

    @Id
    @Column(name = "body_part_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bodyPartId;

    @Column(name = "body_part_name", nullable = false, length = 20)
    private String bodyPartName;

    public BodyPart() {}

    public BodyPart(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public int getBodyPartId() {
        return this.bodyPartId;
    }

    public void setBodyPartId(int bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public String getBodyPartName() {
        return this.bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }
}
