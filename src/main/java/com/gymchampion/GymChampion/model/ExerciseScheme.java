package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise_scheme")
public class ExerciseScheme {

    @Id
    @Column(name = "exercise_scheme_id")
    @GeneratedValue
    private int exerciseSchemeId;

    @Column(name = "exercise_scheme_name", nullable = false, length = 20)
    private String exerciseName;

    @OneToMany(mappedBy = "exerciseScheme")
    private List<Exercise> exercises;

    public ExerciseScheme() {}

    public ExerciseScheme(String exerciseName) { this.exerciseName = exerciseName; }

    public int getExerciseSchemeId() { return this.exerciseSchemeId; }

    public void setExerciseSchemeId(int exerciseSchemeId) { this.exerciseSchemeId = exerciseSchemeId; }

    public String getExerciseName() { return this.exerciseName; }

    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public List<Exercise> getExercises() { return this.exercises; }

    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}
