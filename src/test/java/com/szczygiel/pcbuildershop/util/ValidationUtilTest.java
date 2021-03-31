package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.Item.ItemDto;
import com.szczygiel.pcbuildershop.UserProfile.RegisterDto;
import com.szczygiel.pcbuildershop.UserProfile.UserProfile;
import com.szczygiel.pcbuildershop.UserProfile.UserProfileRepository;
import com.szczygiel.pcbuildershop.category.Category;
import com.szczygiel.pcbuildershop.category.CategoryRepository;
import com.szczygiel.pcbuildershop.security.ApplicationUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ValidationUtilTest {

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void invalidItem_ShouldNotPass() {
        //given
        ItemDto itemDto = new ItemDto(-5L,
                1234L,
                0L,
                LocalDateTime.now().minusMinutes(1),
                "xd".repeat(260),
                "xd".repeat(260),
                BigDecimal.valueOf(-100L)
        );
        //when
        String response = validationUtil.validateItemDto(itemDto);
        //then
        assertEquals("Invalid userId. Invalid categoryId. Invalid title. Invalid description.", response);
    }

    @Test
    public void validItem_shouldPass() {
        //given
        Category addedCategory = categoryRepository.save(DataTestSamples.getCategorySamples().get(0));
        UserProfile addedUser = userProfileRepository.save(DataTestSamples.getUserProfileSamples().get(0));
        ItemDto itemDto = new ItemDto(null,
                addedCategory.getId(),
                addedUser.getId(),
                LocalDateTime.now(),
                "valid titile",
                "valid description",
                BigDecimal.valueOf(100));
        //when
        String response = validationUtil.validateItemDto(itemDto);
        //then
        assertEquals("", response);
    }

    @Test
    public void invalidTitle_shouldNotPass() {
        //given
        Category addedCategory = categoryRepository.save(DataTestSamples.getCategorySamples().get(0));
        UserProfile addedUser = userProfileRepository.save(DataTestSamples.getUserProfileSamples().get(0));
        ItemDto itemDto = new ItemDto(null,
                addedCategory.getId(),
                addedUser.getId(),
                LocalDateTime.now(),
                "invalid titile".repeat(260),
                "valid title",
                BigDecimal.valueOf(100));
        //when
        String response = validationUtil.validateItemDto(itemDto);
        //then
        assertEquals("Invalid title. ", response);
    }

    @Test
    public void invalidDescription_shouldNotPass() {
        //given
        Category addedCategory = categoryRepository.save(DataTestSamples.getCategorySamples().get(0));
        UserProfile addedUser = userProfileRepository.save(DataTestSamples.getUserProfileSamples().get(0));
        ItemDto itemDto = new ItemDto(null,
                addedCategory.getId(),
                addedUser.getId(),
                LocalDateTime.now(),
                "valid titile",
                "xd".repeat(260),
                BigDecimal.valueOf(100));
        //when
        String response = validationUtil.validateItemDto(itemDto);
        //then
        assertEquals("Invalid description.", response);
    }

    @Test
    public void invalidDescriptionAndTitle_shouldNotPass() {
        //given
        Category addedCategory = categoryRepository.save(DataTestSamples.getCategorySamples().get(2));
        UserProfile addedUser = userProfileRepository.save(DataTestSamples.getUserProfileSamples().get(2));
        ItemDto itemDto = new ItemDto(null,
                addedCategory.getId(),
                addedUser.getId(),
                LocalDateTime.now(),
                "title".repeat(260),
                "xd".repeat(260),
                BigDecimal.valueOf(10.20));
        //when
        String response = validationUtil.validateItemDto(itemDto);
        //then
        assertEquals("Invalid title. Invalid description.", response);
    }

    @Test
    public void validRegisterDto_shouldPass() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        //when
        boolean isValid = validationUtil.isRegisterDtoValid(registerDto);
        //then
        assertTrue(isValid);
    }

    @Test
    public void usernameAlreadyUsed_shouldPass() {
        //given
        UserProfile userProfile = new UserProfile(null,
                DataTestSamples.getRegisterDtoSamples().get(0).getUsername(),
                "qwerty123", ApplicationUserRole.USER, "notusedemail@gmail.com", null, false, false);
        userProfileRepository.save(userProfile);
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        //when
        boolean isValid = validationUtil.isRegisterDtoValid(registerDto);
        //then
        assertFalse(isValid);
    }

    @Test
    public void emailAlreadyUsed_shouldPass() {
        //given
        UserProfile userProfile = new UserProfile(null,
                "notusedemail123",
                "qwerty123", ApplicationUserRole.USER,
                DataTestSamples.getRegisterDtoSamples().get(1).getEmail(), null, false, false);
        userProfileRepository.save(userProfile);
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(1);
        //when
        boolean isValid = validationUtil.isRegisterDtoValid(registerDto);
        //then
        assertFalse(isValid);
    }
}
