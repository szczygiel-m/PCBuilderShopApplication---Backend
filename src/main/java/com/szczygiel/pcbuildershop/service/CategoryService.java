package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
