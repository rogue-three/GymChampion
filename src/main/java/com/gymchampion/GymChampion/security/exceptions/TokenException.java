package com.gymchampion.GymChampion.security.exceptions;

public class TokenException extends Exception {

    public TokenException() {
        super("Must log again! Session expired!");
    }
}
