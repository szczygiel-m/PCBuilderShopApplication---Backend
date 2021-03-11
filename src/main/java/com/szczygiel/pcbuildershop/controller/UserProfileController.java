package com.szczygiel.pcbuildershop.controller;

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
    public String login(@RequestBody String userName, @RequestBody String password) {
        return userProfileService.login(userName, password);
    }

    @PostMapping("register")
    public UserProfile register(UserProfile userProfile) {
        return userProfileService.registerUser(userProfile);
    }
}
