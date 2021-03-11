package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<ItemDto> getItems(@RequestParam Long categoryId) {
        return categoryService.getItemsByCategory(categoryId);
    }

    @PostMapping()
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
