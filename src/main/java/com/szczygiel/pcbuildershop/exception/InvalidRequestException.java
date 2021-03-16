package com.szczygiel.pcbuildershop.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String errorsMessages) {
        super(errorsMessages);
    }
}
