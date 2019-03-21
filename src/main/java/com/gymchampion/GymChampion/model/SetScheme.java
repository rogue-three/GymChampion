package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name = "set_scheme")
public class SetScheme {

    @Id
    @Column(name = "set_scheme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int setSchemeId;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

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

    public Training getTraining() { return this.training; }

    public void setTraining(Training training) { this.training = training; }

    public Exercise getExercise() { return this.exercise; }

    public void setExercise(Exercise exercise) { this.exercise = exercise; }
}
