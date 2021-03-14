package com.szczygiel.pcbuildershop.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userID) {
        super("Could not find user with id = " + userID);
    }
}
