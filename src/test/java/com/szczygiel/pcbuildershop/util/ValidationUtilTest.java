package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.service.CategoryService;
import com.szczygiel.pcbuildershop.service.UserProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ValidationUtilTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ValidationUtil validationUtil;

    @PersistenceContext
    private EntityManager entityManager;

//    @AfterEach
//    public void teardown() {
//        entityManager
//                .createNativeQuery("drop database test");
//    }


    @Test
    public void validItem_ShouldPassValidation() {
        userProfileService.registerUser(DataTestSamples.getRegisterDtoSamples().get(0));
        categoryService.addCategory(DataTestSamples.getCategoryDtoSamples().get(0));
        ItemDto itemDto = DataTestSamples.getItemDtoSamples().get(0);

        String response = validationUtil.validateItemDto(itemDto);

        assertEquals("", response);
    }

    @Test
    public void invalidItem_ShouldNotPass() {
        ItemDto itemDto = new ItemDto(-5L,
                1234L,
                0L,
                LocalDateTime.now().minusMinutes(1),
                "xd".repeat(260),
                "xd".repeat(260),
                BigDecimal.valueOf(-100L)
        );

        String response = validationUtil.validateItemDto(itemDto);

        assertEquals("Invalid userId. Invalid categoryId. Invalid title. Invalid description.", response);
    }

}
