package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.dto.LoginDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.service.UserProfileService;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("{userID}")
    public Optional<UserProfile> getUser(@PathVariable Long userID) {
        return userProfileService.getUserProfile(userID);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto) {
        return userProfileService.login(loginDto);
    }

    @PostMapping("register")
    public UserProfile register(RegisterDto userProfile) {
        return userProfileService.registerUser(userProfile);
    }
}
