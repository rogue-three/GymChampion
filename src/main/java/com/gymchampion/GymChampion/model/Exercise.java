package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    public Exercise(String exerciseName, int maxReps, double maxWeight, BodyPart bodyPart, Equipment equipment, ExerciseScheme exerciseScheme) {
        this.exerciseName = exerciseName;
        this.maxReps = maxReps;
        this.maxWeight = maxWeight;
        this.bodyPart = bodyPart;
        this.equipment = equipment;
        this.exerciseScheme = exerciseScheme;
    }

    public Exercise() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id")
    private int exerciseId;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "max_reps", nullable = false)
    private int maxReps;

    @Column(name = "max_weight", nullable = false)
    private double maxWeight;

    @OneToOne
    @Column(name = "body_part", nullable = false)
    private BodyPart bodyPart;

    @OneToOne
    @Column(name = "equipment", nullable = false)
    private Equipment equipment;

    @OneToOne
    @Column(name = "exercise_scheme", nullable = false)
    private ExerciseScheme exerciseScheme;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(int maxReps) {
        this.maxReps = maxReps;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public ExerciseScheme getExerciseScheme() {
        return exerciseScheme;
    }

    public void setExerciseScheme(ExerciseScheme exerciseScheme) {
        this.exerciseScheme = exerciseScheme;
    }
}
