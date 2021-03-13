package com.szczygiel.pcbuildershop.dto;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.model.UserProfile;

public class Converter {

    public static UserProfile registerDtoToUser(RegisterDto registerDto) {
        UserProfile newUser = new UserProfile();
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(registerDto.getPassword());
        newUser.setUsername(registerDto.getUsername());

        return newUser;
    }

    public static ItemDto itemToItemDto(Item item) {
        return ItemDto.builder()
                .Id(item.getId())
                .description(item.getDescription())
                .title(item.getTitle())
                .price(item.getPrice())
                .userProfile(userProfileToDto(item.getUserProfile()))
                .created(item.getCreated()).build();
    }

    public static UserProfileDto userProfileToDto(UserProfile userProfile) {
        return UserProfileDto.builder()
                .Id(userProfile.getId())
                .email(userProfile.getEmail())
                .password(userProfile.getPassword())
                .username(userProfile.getUsername())
                .build();
    }
}
