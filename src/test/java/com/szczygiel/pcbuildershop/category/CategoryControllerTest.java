package com.szczygiel.pcbuildershop.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.UserProfile.LoginDto;
import com.szczygiel.pcbuildershop.UserProfile.UserProfile;
import com.szczygiel.pcbuildershop.UserProfile.UserProfileRepository;
import com.szczygiel.pcbuildershop.UserProfile.UserProfileService;
import com.szczygiel.pcbuildershop.security.ApplicationUserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CategoryControllerTest {

    private static final String categoryControllerPath = "/api/v1/category";
    private static String token;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        passwordEncoder = new BCryptPasswordEncoder();
        UserProfile userProfile = new UserProfile(null,
                "admin123",
                passwordEncoder.encode("password123"),
                ApplicationUserRole.ADMIN,
                "wlodzimierzkwiat12@gmail.com",
                null,
                false,
                true);
        userProfileRepository.save(userProfile);
        token = userProfileService.login(new LoginDto("admin123", "password123"));
    }

     /*
        POST api/v1/category
     */

    @Test
    public void addCategoryWithEmptyBody_ShouldReturnBadRequest() throws Exception {
        //given
        //when
        MockHttpServletResponse response = mockMvc.perform(post(categoryControllerPath)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void addCategoryWithNoAuthorities_ShouldReturnForbidden() throws Exception {
        //given
        //when
        MockHttpServletResponse response = mockMvc.perform(post(categoryControllerPath)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    }

    @Test
    public void addCategory_ShouldReturnCreated() throws Exception {
        //given
        CategoryDto category = DataTestSamples.getCategoryDtoSamples().get(0);
        String bodyContent = objectMapper.writeValueAsString(category);
        //when
        MockHttpServletResponse response = mockMvc.perform(post(categoryControllerPath)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}