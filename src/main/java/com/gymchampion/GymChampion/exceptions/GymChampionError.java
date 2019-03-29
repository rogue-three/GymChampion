package com.gymchampion.GymChampion.exceptions;

public class GymChampionError {

    private String errorMessage;

    public GymChampionError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
