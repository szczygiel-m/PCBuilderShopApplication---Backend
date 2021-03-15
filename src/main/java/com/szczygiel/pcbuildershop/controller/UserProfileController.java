package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.dto.GetUserDto;
import com.szczygiel.pcbuildershop.dto.LoginDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.exception.InvalidLoginException;
import com.szczygiel.pcbuildershop.exception.InvalidRegisterException;
import com.szczygiel.pcbuildershop.exception.UserNotFoundException;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.service.UserProfileService;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final ValidationUtil validationUtil;

    public UserProfileController(UserProfileService userProfileService, ValidationUtil validationUtil) {
        this.userProfileService = userProfileService;
        this.validationUtil = validationUtil;
    }

    @GetMapping("{userId}")
    public GetUserDto getUser(@PathVariable Long userId) {
        return userProfileService.getUserProfile(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PostMapping("login")
    public String login(@Valid @RequestBody LoginDto loginDto, @ApiIgnore Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidLoginException(validationUtil.getErrorsMessages(errors));
        }

        return userProfileService.login(loginDto);
    }

    @PostMapping("register")
    public UserProfile register(@Valid @RequestBody RegisterDto userProfile, @ApiIgnore Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidRegisterException(validationUtil.getErrorsMessages(errors));
        }

        return userProfileService.registerUser(userProfile);
    }
}
