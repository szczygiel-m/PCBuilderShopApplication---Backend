package com.szczygiel.pcbuildershop.service;

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

    public Optional<UserProfile> getUserProfile(Long userID){
        return userProfileRepository.findById(userID);
    }

    public String login(String userName, String password) {
        UserProfile userProfile = userProfileRepository.findByUsername(userName);

        if(userProfile != null){
            if(userProfile.getPassword().equals(password)) {
                return "Succesfuly logged in!";
            }
        }

        return "Invalid Field 'username' or 'password'";
    }

    public UserProfile registerUser(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}
