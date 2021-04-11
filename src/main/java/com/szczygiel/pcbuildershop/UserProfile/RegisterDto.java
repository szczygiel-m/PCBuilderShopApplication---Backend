package com.szczygiel.pcbuildershop.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @Size(min = 4, max = 32, message = "Field 'username' should be minimum 4 chars and maximum 32 chars.")
    private String username;

    @Size(min = 8, message = "Field 'password' should be minimum 8.")
    private String password;

    @Email(message = "Email should be valid.")
    private String email;
}
