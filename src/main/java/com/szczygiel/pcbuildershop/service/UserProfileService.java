package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.dto.Converter;
import com.szczygiel.pcbuildershop.dto.LoginDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Optional<UserProfile> getUserProfile(Long userId){
        return userProfileRepository.findById(userId);
    }

    public String login(LoginDto loginDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginDto.getUsername());
        if(userProfile != null){
            if(userProfile.getPassword().equals(loginDto.getPassword())) {
                return "Succesfuly logged in!";
            }
        }
        return "Invalid Field 'username' or 'password'";
    }

    public UserProfile registerUser(RegisterDto userProfile) {
        return userProfileRepository.save(Converter.registerDtoToUser(userProfile));
    }
}
