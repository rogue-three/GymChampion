package com.gymchampion.GymChampion.security.exceptions;

public class UncorrectPasswordException extends Exception {

    public UncorrectPasswordException() {
        super("Password is incorrect!");
    }
}
