package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {

    @Size(min = 8, max = 32, message = "Field 'username' should be minimum 8 chars and maximum 32 chars.")
    private String username;

    @Size(min = 8, max = 32, message = "Field 'username' should be minimum 8 chars and maximum 32 chars.")
    private String password;
}
