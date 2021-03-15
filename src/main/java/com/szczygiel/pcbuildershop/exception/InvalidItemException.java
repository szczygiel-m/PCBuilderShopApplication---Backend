package com.szczygiel.pcbuildershop.exception;

public class InvalidItemException extends RuntimeException {

    public InvalidItemException(String errors) {
        super(errors);
    }
}
