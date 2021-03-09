package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import com.szczygiel.pcbuildershop.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public CategoryService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    public Iterable<Item> getAllItemsByCategory(String category){
        return itemRepository.findAllByCategory(category);
    }
}
