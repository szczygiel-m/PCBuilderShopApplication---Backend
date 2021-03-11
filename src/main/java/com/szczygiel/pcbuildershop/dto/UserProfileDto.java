package com.szczygiel.pcbuildershop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDto {

    private final Long Id;
    private final String username;
    private final String password;
    private final String email;
}
