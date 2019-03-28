package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name = "exercise_scheme")
public class ExerciseScheme {

    @Id
    @Column(name = "exercise_scheme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseSchemeId;

    @Column(name = "exercise_scheme_name", nullable = false, length = 20)
    private String exerciseSchemeName;

    public ExerciseScheme() {}

    public ExerciseScheme(String exerciseSchemeName) { this.exerciseSchemeName = exerciseSchemeName; }

    public int getExerciseSchemeId() { return this.exerciseSchemeId; }

    public void setExerciseSchemeId(int exerciseSchemeId) { this.exerciseSchemeId = exerciseSchemeId; }

    public String getExerciseName() { return this.exerciseSchemeName; }

    public void setExerciseName(String exerciseName) { this.exerciseSchemeName = exerciseName; }
}
