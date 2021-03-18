package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.dto.CategoryDto;
import com.szczygiel.pcbuildershop.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @PersistenceContext
    private EntityManager entityManager;

//    @AfterEach
//    public void teardown() {
//        entityManager
//                .createNativeQuery("drop database test");
//    }


    @Test
    public void validCategory_ShouldInsertToDb() {
        CategoryDto categoryDto = DataTestSamples.getCategoryDtoSamples().get(0);
        Category addedCategory = categoryService.addCategory(categoryDto);

        assertNotNull(addedCategory);
        assertEquals(categoryDto.getCategory(), addedCategory.getCategory());
        assertEquals(categoryDto.getDescription(), addedCategory.getDescription());
    }

}
