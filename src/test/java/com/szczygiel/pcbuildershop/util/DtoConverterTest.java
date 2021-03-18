package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.dto.CategoryDto;
import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.model.UserProfile;
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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DtoConverterTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    DtoConverter converter;

    @PersistenceContext
    private EntityManager entityManager;

//    @AfterEach
//    public void teardown() {
//        entityManager
//                .createNativeQuery("drop database test");
//    }


    @Test
    public void validItem_shouldConvertToItemDto() {
        Item item = new Item(1L,
                converter.categoryDtoToCategory(DataTestSamples.getCategoryDtoSamples().get(0)),
                converter.registerDtoToUser(DataTestSamples.getRegisterDtoSamples().get(0)),
                "Przedmiot testowy",
                "Sluzy tylko do testow",
                LocalDateTime.now(),
                BigDecimal.valueOf(100));
        ItemDto itemDto = converter.itemToItemDto(item);

        assertNotNull(itemDto);
        assertEquals(BigDecimal.valueOf(100), itemDto.getPrice());
        assertEquals(item.getCategory().getId(), itemDto.getCategoryId());
        assertEquals("Przedmiot testowy", itemDto.getTitle());
        assertEquals("Przedmiot testowy", itemDto.getTitle());
    }


    @Test
    public void validRegisterDto_shouldConvertToUserProfile() {
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);

        UserProfile userProfile = converter.registerDtoToUser(registerDto);

        assertNotNull(userProfile);
        assertEquals(registerDto.getUsername(), userProfile.getUsername());
        assertEquals(registerDto.getPassword(), userProfile.getPassword());
        assertEquals(registerDto.getEmail(), userProfile.getEmail());
        assertNull(userProfile.getItems());
    }

    @Test
    public void validCategoryDto_shouldConvertToCategory() {
        CategoryDto categoryDto = DataTestSamples.getCategoryDtoSamples().get(0);

        Category newCategory = converter.categoryDtoToCategory(categoryDto);

        assertNotNull(newCategory);
        assertEquals(categoryDto.getCategory(), newCategory.getCategory());
        assertEquals(categoryDto.getDescription(), newCategory.getDescription());
        assertNull(newCategory.getItems());
    }

    @Test
    public void inValidItemDto_shouldThrowException() {
        ItemDto itemDto = DataTestSamples.getItemDtoSamples().get(0);

        assertThrows(NoSuchElementException.class, () -> converter.itemDtoToItem(itemDto));
    }

}
