package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @Column(name = "exercise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

    @Column(name = "exercise_name", length = 20, unique = true, nullable = false)
    private String exerciseName;

    @Column(name = "max_reps", nullable = false)
    private int maxReps;

    @Column(name = "max_weight", nullable = false)
    private double maxWeight;

    @OneToMany(mappedBy = "exercise")
    private List<SetScheme> setSchemes;

    @ManyToOne
    @JoinColumn(name = "body_part_id")
    private BodyPart bodyPart;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "exercise_scheme_id")
    private ExerciseScheme exerciseScheme;

    public Exercise() {}

    public Exercise(String exerciseName, int maxReps, double maxWeight) {
        this.exerciseName = exerciseName;
        this.maxReps = maxReps;
        this.maxWeight = maxWeight;
    }

    public int getExerciseId() { return this.exerciseId; }

    public void setExerciseId(int exerciseId) { this.exerciseId = exerciseId; }

    public String getExerciseName() { return this.exerciseName; }

    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public int getMaxReps() { return this.maxReps; }

    public void setMaxReps(int maxReps) { this.maxReps = maxReps; }

    public double getMaxWeight() { return this.maxWeight; }

    public void setMaxWeight(double maxWeight) { this.maxWeight = maxWeight; }

    public List<SetScheme> getSetSchemes() { return this.setSchemes; }

    public void setSetSchemes(List<SetScheme> setSchemes) { this.setSchemes = setSchemes; }

    public BodyPart getBodyPart() { return this.bodyPart; }

    public void setBodyPart(BodyPart bodyPart) { this.bodyPart = bodyPart; }

    public Equipment getEquipment() { return this.equipment; }

    public void setEquipment(Equipment equipment) { this.equipment = equipment; }

    public ExerciseScheme getExerciseScheme() { return this.exerciseScheme; }

    public void setExerciseScheme(ExerciseScheme exerciseScheme) { this.exerciseScheme = exerciseScheme; }
}
