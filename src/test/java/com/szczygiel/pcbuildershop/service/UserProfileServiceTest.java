package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.dto.LoginDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @PersistenceContext
    private EntityManager entityManager;

//    @AfterEach
//    public void teardown() {
//        entityManager
//                .createNativeQuery("drop database test");
//    }

    @Test
    public void registerUserShouldInsertToDb() {
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        UserProfile newUser = userProfileService.registerUser(registerDto);

        assertNotNull(newUser);
        assertEquals(newUser.getUsername(), registerDto.getUsername());
        assertEquals(newUser.getEmail(), registerDto.getEmail());
        assertNull(newUser.getItems());
        assertNotNull(newUser.getId());
    }


    @Test
    public void validLogin_ShouldLogIn() {
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(0);

        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);

        assertEquals("Successfully logged in!", response);
    }

    @Test
    public void invalidPasswordAndUsername_ShouldNotLogIn() {
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(1);

        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);

        assertEquals("Invalid Field 'username' or 'password'.", response);
    }

    @Test
    public void invalidPassword_ShouldNotLogIn() {
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(1);

        loginDto.setPassword("wlodek123");

        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);

        assertEquals("Invalid Field 'username' or 'password'.", response);
    }

    @Test
    public void invalidUsername_ShouldNotLogIn() {
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(1);

        loginDto.setUsername("kwiatkowski");

        userProfileService.registerUser(registerDto);
        String response = userProfileService.login(loginDto);

        assertEquals("Invalid Field 'username' or 'password'.", response);
    }

}
