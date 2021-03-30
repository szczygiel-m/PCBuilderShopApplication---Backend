package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.category.Category;
import com.szczygiel.pcbuildershop.category.CategoryDto;
import com.szczygiel.pcbuildershop.category.CategoryService;
import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.Item.Item;
import com.szczygiel.pcbuildershop.Item.ItemDto;
import com.szczygiel.pcbuildershop.UserProfile.RegisterDto;
import com.szczygiel.pcbuildershop.UserProfile.UserProfile;
import com.szczygiel.pcbuildershop.UserProfile.UserProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public void validItem_shouldConvertToItemDto() {
        //given
        Item item = new Item(1L,
                converter.categoryDtoToCategory(DataTestSamples.getCategoryDtoSamples().get(0)),
                converter.registerDtoToUser(DataTestSamples.getRegisterDtoSamples().get(0)),
                "Przedmiot testowy",
                "Sluzy tylko do testow",
                LocalDateTime.now(),
                BigDecimal.valueOf(100));
        //when
        ItemDto itemDto = converter.itemToItemDto(item);
        //then
        assertNotNull(itemDto);
        assertEquals(BigDecimal.valueOf(100), itemDto.getPrice());
        assertEquals(item.getCategory().getId(), itemDto.getCategoryId());
        assertEquals("Przedmiot testowy", itemDto.getTitle());
        assertEquals("Przedmiot testowy", itemDto.getTitle());
    }


    @Test
    public void validRegisterDto_shouldConvertToUserProfile() {
        //given
        RegisterDto registerDto = DataTestSamples.getRegisterDtoSamples().get(0);
        //when
        UserProfile userProfile = converter.registerDtoToUser(registerDto);
        //then
        assertNotNull(userProfile);
        assertEquals(registerDto.getUsername(), userProfile.getUsername());
        assertEquals(registerDto.getPassword(), userProfile.getPassword());
        assertEquals(registerDto.getEmail(), userProfile.getEmail());
        assertNull(userProfile.getItems());
    }

    @Test
    public void validCategoryDto_shouldConvertToCategory() {
        //given
        CategoryDto categoryDto = DataTestSamples.getCategoryDtoSamples().get(0);
        //when
        Category newCategory = converter.categoryDtoToCategory(categoryDto);
        //then
        assertNotNull(newCategory);
        assertEquals(categoryDto.getCategory(), newCategory.getCategory());
        assertEquals(categoryDto.getDescription(), newCategory.getDescription());
        assertNull(newCategory.getItems());
    }

    @Test
    public void inValidItemDto_shouldThrowException() {
        //given
        //when
        ItemDto itemDto = DataTestSamples.getItemDtoSamples().get(0);
        //then
        assertThrows(NoSuchElementException.class, () -> converter.itemDtoToItem(itemDto));
    }

}
