package com.gymchampion.GymChampion.access.exceptions;

public class UncorrectPasswordException extends Exception {

    public UncorrectPasswordException() {
        super("Password is incorrect!");
    }
}
