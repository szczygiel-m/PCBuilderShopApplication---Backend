package com.szczygiel.pcbuildershop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @Size(min = 8, max = 32, message = "Field 'username' should be minimum 8 chars and maximum 32 chars.")
    private String username;

    @Size(min = 8, max = 32, message = "Field 'username' should be minimum 8 chars and maximum 32 chars.")
    private String password;
}
