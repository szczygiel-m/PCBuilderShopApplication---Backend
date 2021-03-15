package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class RegisterDto {

    @Size(min = 8, max = 32, message = "Field 'username' should be minimum 8 chars and maximum 32 chars.")
    private final String username;

    @Size(min = 8, max = 32, message = "Field 'password' should be minimum 8 chars and maximum 32 chars.")
    private final String password;

    @Email(message = "Email should be valid.")
    private final String email;
}
