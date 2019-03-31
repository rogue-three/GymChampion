package com.gymchampion.GymChampion.exceptions;

public class ResourceDoesNotExistException extends Exception {

    public ResourceDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
