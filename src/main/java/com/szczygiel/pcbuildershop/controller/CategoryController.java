package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{category}")
    public ResponseEntity<Iterable<Item>> getItems(@PathVariable String category) {
        return new ResponseEntity<>(categoryService.getAllItemsByCategory(category), HttpStatus.OK);
    }
}
