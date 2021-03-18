package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.dto.CategoryDto;
import com.szczygiel.pcbuildershop.util.DtoConverter;
import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DtoConverter converter;

    public CategoryService(CategoryRepository categoryRepository, DtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public Category addCategory(CategoryDto category) {
        return categoryRepository.save(converter.categoryDtoToCategory(category));
    }
}
