package com.szczygiel.pcbuildershop.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponseBody {

    private String status;
    private String message;
    private LocalDateTime timestamp;
}
