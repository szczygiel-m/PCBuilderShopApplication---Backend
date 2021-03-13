package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginDto {

    private final String username;
    private final String password;
}
