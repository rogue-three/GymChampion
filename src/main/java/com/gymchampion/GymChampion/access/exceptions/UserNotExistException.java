package com.gymchampion.GymChampion.access.exceptions;

public class UserNotExistException extends Exception {

    public UserNotExistException() {
        super("User doesn't exist!");
    }

}
