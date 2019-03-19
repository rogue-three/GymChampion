package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "set_scheme")
public class SetScheme {

    @Id
    @Column(name = "set_scheme_id")
    @GeneratedValue
    private int setSchemeId;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private double weight;

    @ManyToMany(mappedBy = "setSchemes")
    private List<Training> trainings;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public SetScheme() {}

    public SetScheme(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public int getSetSchemeId() { return this.setSchemeId; }

    public void setSetSchemeId(int setSchemeId) { this.setSchemeId = setSchemeId; }

    public int getReps() { return this.reps; }

    public void setReps(int reps) { this.reps = reps; }

    public double getWeight() { return this.weight; }

    public void setWeight(double weight) { this.weight = weight; }

    public List<Training> getTrainings() { return this.trainings; }

    public void setTrainings(List<Training> trainings) { this.trainings = trainings; }

    public Exercise getExercise() { return this.exercise; }

    public void setExercise(Exercise exercise) { this.exercise = exercise; }
}
