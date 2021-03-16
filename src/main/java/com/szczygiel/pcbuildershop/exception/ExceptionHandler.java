package com.szczygiel.pcbuildershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    private final ExceptionResponseBody responseBody = new ExceptionResponseBody();

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(
            {UserNotFoundException.class,
                    ItemNotFoundException.class})
    public ExceptionResponseBody notFoundHandler(RuntimeException e) {
        responseBody.setMessage(e.getMessage());
        responseBody.setTimestamp(LocalDateTime.now());
        responseBody.setStatus("404");

        return responseBody;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(
            {InvalidItemException.class,
            InvalidLoginException.class,
            InvalidCategoryException.class,
            InvalidRegisterException.class,
            InvalidRequestException.class}
    )
    public ExceptionResponseBody badRequestHandler(RuntimeException e) {
        responseBody.setMessage(e.getMessage());
        responseBody.setTimestamp(LocalDateTime.now());
        responseBody.setStatus("400");

        return responseBody;
    }
}
