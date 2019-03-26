package com.gymchampion.GymChampion.security.exceptions;

public class UserNotExistException extends Exception {

    public UserNotExistException() {
        super("User doesn't exist!");
    }

}
