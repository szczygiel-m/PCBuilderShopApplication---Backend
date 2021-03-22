package com.szczygiel.pcbuildershop.Category;

import com.szczygiel.pcbuildershop.exception.InvalidCategoryException;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryController(CategoryService categoryService, ValidationUtil validationUtil) {
        this.categoryService = categoryService;
        this.validationUtil = validationUtil;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@Valid @RequestBody CategoryDto category, @ApiIgnore Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidCategoryException(validationUtil.getErrorsMessages(errors));
        }

        return categoryService.addCategory(category);
    }
}
