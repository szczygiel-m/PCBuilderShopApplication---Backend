package com.szczygiel.pcbuildershop.UserProfile;

import com.szczygiel.pcbuildershop.exception.ForbiddenException;
import com.szczygiel.pcbuildershop.exception.InvalidLoginException;
import com.szczygiel.pcbuildershop.exception.InvalidRegisterException;
import com.szczygiel.pcbuildershop.exception.UserNotFoundException;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final ValidationUtil validationUtil;

    public UserProfileController(UserProfileService userProfileService, ValidationUtil validationUtil) {
        this.userProfileService = userProfileService;
        this.validationUtil = validationUtil;
    }

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userProfileService.getUserProfile(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PostMapping("login")
    public String login(@Valid @RequestBody LoginDto loginDto, @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidLoginException(validationUtil.getErrorsMessages(errors));
        }

        String loginResponse = userProfileService.login(loginDto);
        if (!loginResponse.startsWith("Bearer ")) {
            throw new ForbiddenException(loginResponse);
        }

        return loginResponse;
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody RegisterDto userProfile, @ApiIgnore Errors errors) {
        if (!validationUtil.isRegisterDtoValid(userProfile)) {
            throw new InvalidRegisterException("Invalid register request.");
        }
        if (errors.hasErrors()) {
            throw new InvalidRegisterException(validationUtil.getErrorsMessages(errors));
        }

        return "Successfully registered!";
    }
}
