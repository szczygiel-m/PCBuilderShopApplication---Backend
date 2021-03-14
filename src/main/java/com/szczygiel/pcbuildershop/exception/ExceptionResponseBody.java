package com.szczygiel.pcbuildershop.exception;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class ExceptionResponseBody {

    private String status;
    private String message;
    private Timestamp timestamp;
}
