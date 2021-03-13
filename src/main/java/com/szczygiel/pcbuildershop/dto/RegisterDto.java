package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RegisterDto {

    private final String username;
    private final String password;
    private final String email;
}
