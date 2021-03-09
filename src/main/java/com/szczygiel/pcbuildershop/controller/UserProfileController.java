package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.service.UserProfileService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("{userID}")
    public ResponseEntity<Optional<UserProfile>> getUser(@PathVariable Long userID) {
        return new ResponseEntity<>(userProfileService.getUserProfile(userID), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody String userName, @RequestBody String password) {
        return new ResponseEntity<>(userProfileService.login(userName, password), HttpStatus.OK);
    }

    @PostMapping("register")
    public NotYetImplementedException register() {
        return new NotYetImplementedException();
    }
}
