package com.szczygiel.pcbuildershop.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;
}
