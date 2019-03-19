package com.gymchampion.GymChampion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercise_scheme")
public class ExerciseScheme {

    public ExerciseScheme(int exerciseSchemeId, String schemeName) {
        this.exerciseSchemeId = exerciseSchemeId;
        this.schemeName = schemeName;
    }

    public ExerciseScheme() {
    }

    @Id
    @Column(name = "exercise_scheme_id")
    private int exerciseSchemeId;

    // scheme name is UpperBody and LowerBody
    @Column(nullable = false)
    private String schemeName;
}
