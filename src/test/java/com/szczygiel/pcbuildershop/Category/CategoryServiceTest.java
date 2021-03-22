package com.szczygiel.pcbuildershop.Category;

import com.szczygiel.pcbuildershop.DataTestSamples;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void validCategory_ShouldInsertToDb() {
        //given
        //when
        CategoryDto categoryDto = DataTestSamples.getCategoryDtoSamples().get(0);
        Category addedCategory = categoryService.addCategory(categoryDto);
        //then
        assertNotNull(addedCategory);
        assertEquals(categoryDto.getCategory(), addedCategory.getCategory());
        assertEquals(categoryDto.getDescription(), addedCategory.getDescription());
    }
}
