package com.gymchampion.GymChampion.exceptions;

public class UserNotExistException extends Exception {

    public UserNotExistException() {
        super("User doesn't exist!");
    }

}
