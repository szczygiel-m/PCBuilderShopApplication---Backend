package com.szczygiel.pcbuildershop.dto;

import com.szczygiel.pcbuildershop.model.UserProfile;

public class Converter {

    public static UserProfile registerDtoToUser(RegisterDto registerDto) {
        UserProfile newUser = new UserProfile();
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(registerDto.getPassword());
        newUser.setUsername(registerDto.getUsername());

        return newUser;
    }

}
