package com.gymchampion.GymChampion.model;

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

    @Column(name = "user_weight")
    private double userWeight;

    @Column(name = "archivized", nullable = false)
    private boolean archivized;

    @ManyToOne
    @JoinColumn(name = "user_login", nullable = false)
    private User user;

    @OneToMany(mappedBy = "training")
    private List<SetScheme> setSchemes = new ArrayList<>();

    public Training() {}

    public Training(Date trainingDate, double userWeight, boolean archivized) {
        this.archivized = archivized;
        this.trainingDate = trainingDate;
        this.userWeight = userWeight;
    }

    public Training(boolean archivized) {
        this.archivized = archivized;
    }

    public int getTrainingId() { return this.trainingId; }

    public void setTrainingId(int trainingId) { this.trainingId = trainingId; }

    public Date getTrainingDate() { return this.trainingDate; }

    public void setTrainingDate(Date trainingDate) { this.trainingDate = trainingDate; }

    public double getUserWeight() { return this.userWeight; }

    public void setUserWeight(double userWeight) { this.userWeight = userWeight; }

    public boolean isArchivized() { return this.archivized; }

    public void setArchivized(boolean archivized) { this.archivized = archivized; }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }

    public List<SetScheme> getSetSchemes() { return this.setSchemes; }

    public void setSetSchemes(List<SetScheme> setSchemes) { this.setSchemes = setSchemes; }
}
