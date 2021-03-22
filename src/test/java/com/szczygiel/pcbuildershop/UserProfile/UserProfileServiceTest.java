package com.szczygiel.pcbuildershop.UserProfile;

import com.szczygiel.pcbuildershop.DataTestSamples;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @Test
    public void registerUserShouldInsertToDb() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        //when
        UserProfile newUser = userProfileService.registerUser(registerDto);
        //then
        assertNotNull(newUser);
        assertEquals(newUser.getUsername(), registerDto.getUsername());
        assertEquals(newUser.getEmail(), registerDto.getEmail());
        assertNull(newUser.getItems());
        assertNotNull(newUser.getId());
    }

    @Test
    public void validLogin_ShouldLogIn() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(0);
        //when
        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);
        //then
        assertEquals("Successfully logged in!", response);
    }

    @Test
    public void invalidPasswordAndUsername_ShouldNotLogIn() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(1);
        //when
        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);
        //then
        assertEquals("Invalid Field 'username' or 'password'.", response);
    }

    @Test
    public void invalidPassword_ShouldNotLogIn() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(1);
        loginDto.setPassword("wlodek123");
        //when
        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);
        //then
        assertEquals("Invalid Field 'username' or 'password'.", response);
    }

    @Test
    public void invalidUsername_ShouldNotLogIn() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(1);
        loginDto.setUsername("kwiatkowski");
        //when
        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);
        //then
        assertEquals("Invalid Field 'username' or 'password'.", response);
    }

}
