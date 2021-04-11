package com.szczygiel.pcbuildershop.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String loginResponse) {
        super(loginResponse);
    }
}
