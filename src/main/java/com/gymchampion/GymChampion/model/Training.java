package com.gymchampion.GymChampion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @Column(name = "training_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainingId;

    @Column(name = "training_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date trainingDate;

    @Column(name = "user_body_weight")
    private double userBodyWeight;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @ManyToOne
    @JoinColumn(name = "user_login", nullable = false)
    private User user;

    @OneToMany(mappedBy = "training")
    @JsonIgnore
    private List<SetScheme> setSchemes = new ArrayList<>();

    public Training() {}

    public Training(Date trainingDate, double userWeight, boolean archived) {
        this.archived = archived;
        this.trainingDate = trainingDate;
        this.userBodyWeight = userWeight;
    }

    public Training(boolean archived) {
        this.archived = archived;
    }

    public int getTrainingId() { return this.trainingId; }

    public void setTrainingId(int trainingId) { this.trainingId = trainingId; }

    public Date getTrainingDate() { return this.trainingDate; }

    public void setTrainingDate(Date trainingDate) { this.trainingDate = trainingDate; }

    public double getUserBodyWeight() { return this.userBodyWeight; }

    public void setUserBodyWeight(double userWeight) { this.userBodyWeight = userWeight; }

    public boolean isArchived() { return this.archived; }

    public void setArchived(boolean archived) { this.archived = archived; }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }

    public List<SetScheme> getSetSchemes() { return this.setSchemes; }

    public void setSetSchemes(List<SetScheme> setSchemes) { this.setSchemes = setSchemes; }
}
