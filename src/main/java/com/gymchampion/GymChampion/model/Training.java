package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @Column(name = "training_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainingId;

    @Column(name = "training_date_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date trainingDateStart;

    @Column(name = "user_body_weight")
    private double userBodyWeight;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @ManyToOne
    @JoinColumn(name = "user_login", nullable = false)
    private User user;

    public Training() {}

    public Training(Date trainingDateStart, double userWeight, boolean archived) {
        this.archived = archived;
        this.trainingDateStart = trainingDateStart;
        this.userBodyWeight = userWeight;
    }

    public Training(boolean archived) {
        this.archived = archived;
    }

    public int getTrainingId() { return this.trainingId; }

    public void setTrainingId(int trainingId) { this.trainingId = trainingId; }

    public Date getTrainingDateStart() { return this.trainingDateStart; }

    public void setTrainingDateStart(Date trainingDateStart) { this.trainingDateStart = trainingDateStart; }

    public double getUserBodyWeight() { return this.userBodyWeight; }

    public void setUserBodyWeight(double userWeight) { this.userBodyWeight = userWeight; }

    public boolean isArchived() { return this.archived; }

    public void setArchived(boolean archived) { this.archived = archived; }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }
}
