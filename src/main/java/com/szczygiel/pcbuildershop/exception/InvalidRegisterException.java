package com.szczygiel.pcbuildershop.exception;

public class InvalidRegisterException extends RuntimeException {
    public InvalidRegisterException(String errorsMessages) {
        super(errorsMessages);
    }
}
