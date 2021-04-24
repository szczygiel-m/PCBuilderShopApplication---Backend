package com.szczygiel.pcbuildershop.UserProfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.security.ApplicationUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserProfileControllerTest {

    private static final String userProfileControllerPath = "/api/v1/users";
    private static String token;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

     /*
        POST /api/v1/user/register
     */

    @Test
    public void registerEmptyBody_ShouldReturnBadRequest() throws Exception {
        //given
        //when
        MockHttpServletResponse response = mockMvc.perform(post(userProfileControllerPath + "/register")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void registerValidBody_ShouldReturnCreated() throws Exception {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        String bodyContent = objectMapper.writeValueAsString(registerDto);
        //when
        MockHttpServletResponse response = mockMvc.perform(post(userProfileControllerPath + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    /*
        POST /api/v1/user/login
     */

    @Test
    public void loginEmptyBody_ShouldReturnBadRequest() throws Exception {
        //given
        //when
        MockHttpServletResponse response = mockMvc.perform(post(userProfileControllerPath + "/login")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void loginValidBody_ShouldReturnOkAndToken() throws Exception {
        //given
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(0);
        userProfileRepository.save(
                new UserProfile(
                        null,
                        loginDto.getUsername(),
                        passwordEncoder.encode(loginDto.getPassword()),
                        ApplicationUserRole.USER,
                        "kwiatkowWlad123@gmail.com",
                        null,
                        false,
                        true
                )
        );
        String bodyContent = objectMapper.writeValueAsString(loginDto);
        //when
        MockHttpServletResponse response = mockMvc.perform(post(userProfileControllerPath + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertTrue(response.getContentAsString().startsWith("Bearer "));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void notExistingUser_ShouldReturnForbidden() throws Exception {
        //given
        LoginDto loginDto = DataTestSamples.getLoginDtoSamples().get(0);
        String bodyContent = objectMapper.writeValueAsString(loginDto);
        //when
        MockHttpServletResponse response = mockMvc.perform(post(userProfileControllerPath + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertFalse(response.getContentAsString().startsWith("Bearer "));
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    }

    /*
        GET /api/v1/user/{userId}
     */

    @Test
    public void notExistingUser_ShouldReturnNotFound() throws Exception {
        //given
        //when
        MockHttpServletResponse response = mockMvc.perform(get(userProfileControllerPath)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void existingUser_ShouldReturnOkAndUser() throws Exception {
        //given
        UserProfile savedUser = userProfileRepository.save(
                DataTestSamples.getUserProfileSamples().get(0)
        );
        String expected = "{\"username\":\"jlowdes0\",\"items\":null}";
        //when
        MockHttpServletResponse response = mockMvc.perform(get(userProfileControllerPath + "/" + savedUser.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(expected, response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
