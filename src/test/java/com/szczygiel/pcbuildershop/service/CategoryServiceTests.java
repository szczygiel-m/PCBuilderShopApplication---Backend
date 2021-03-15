package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.dto.CategoryDto;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @AfterEach
    public void cleanup() {
        entityManager
                .createNativeQuery("truncate table test.category")
                .executeUpdate();
    }

    @Nested
    public class AddCategoryTest {

        @Test
        @Transactional
        public void validInputData_shouldInsertToDb() {
            CategoryDto categoryDto;
        }
    }
}
