package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.util.DtoConverter;
import com.szczygiel.pcbuildershop.dto.UserDto;
import com.szczygiel.pcbuildershop.dto.LoginDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final DtoConverter converter;

    public UserProfileService(UserProfileRepository userProfileRepository, DtoConverter converter) {
        this.userProfileRepository = userProfileRepository;
        this.converter = converter;
    }

    public Optional<UserDto> getUserProfile(Long userId){
        Optional<UserProfile> user = userProfileRepository.findById(userId);

        return user.map(userProfile -> new UserDto(userProfile.getUsername(), userProfile.getItems()));
    }

    public String login(LoginDto loginDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginDto.getUsername());
        if(userProfile != null){
            if(userProfile.getPassword().equals(loginDto.getPassword())) {
                return "Successfully logged in!";
            }
        }
        return "Invalid Field 'username' or 'password'.";
    }

    public UserProfile registerUser(RegisterDto registerDto) {
        return userProfileRepository.save(converter.registerDtoToUser(registerDto));
    }
}
