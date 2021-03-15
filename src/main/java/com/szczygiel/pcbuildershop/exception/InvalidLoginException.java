package com.szczygiel.pcbuildershop.exception;

import lombok.Getter;

@Getter
public class InvalidLoginException extends RuntimeException {


    public InvalidLoginException(String errorMessage) {
        super(errorMessage);
    }
}
