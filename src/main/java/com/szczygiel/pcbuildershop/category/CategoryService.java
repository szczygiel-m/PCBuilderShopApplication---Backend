package com.szczygiel.pcbuildershop.category;

import com.szczygiel.pcbuildershop.util.DtoConverter;
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
