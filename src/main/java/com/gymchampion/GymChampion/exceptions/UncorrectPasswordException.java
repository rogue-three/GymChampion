package com.gymchampion.GymChampion.exceptions;

public class UncorrectPasswordException extends Exception {

    public UncorrectPasswordException() {
        super("Password is incorrect!");
    }
}
