package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue
    private int trainingId;

    @Column(name = "archivized", nullable = false)
    private boolean archivized;

    @Column(name = "training_date", nullable = false)
    private Date trainingDate;

    @Column(name = "user_weight")
    private double userWeight;


    public Training() {
    }

    public Training(boolean archivized, Date trainingDate, double userWeight) {
        this.archivized = archivized;
        this.trainingDate = trainingDate;
        this.userWeight = userWeight;
    }

    public boolean isArchivized() {
        return archivized;
    }

    public void setArchivized(boolean archivized) {
        this.archivized = archivized;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }
}
