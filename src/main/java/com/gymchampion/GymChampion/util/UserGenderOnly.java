package com.gymchampion.GymChampion.util;

import com.gymchampion.GymChampion.model.Gender;

public class UserGenderOnly {

    private Gender userGender;

    public UserGenderOnly(Gender userGender) {
        this.userGender = userGender;
    }

    public Gender getUserGender() {
        return this.userGender;
    }
}
