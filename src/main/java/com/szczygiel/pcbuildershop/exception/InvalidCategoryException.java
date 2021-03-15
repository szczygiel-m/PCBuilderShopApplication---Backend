package com.szczygiel.pcbuildershop.exception;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String errorsMessages) {
        super(errorsMessages);
    }
}
