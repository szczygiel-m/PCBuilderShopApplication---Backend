package com.szczygiel.pcbuildershop.dto;

import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import com.szczygiel.pcbuildershop.repository.UserProfileRepository;

import java.time.LocalDateTime;

public class Converter {

    private static CategoryRepository categoryRepository;
    private static UserProfileRepository userProfileRepository;

    public static UserProfile registerDtoToUser(RegisterDto registerDto) {
        UserProfile newUser = new UserProfile();
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(registerDto.getPassword());
        newUser.setUsername(registerDto.getUsername());

        return newUser;
    }

    public static Category dtoToCategory(CategoryDto category) {
        Category newCategory = new Category();
        newCategory.setCategory(category.getCategory());
        newCategory.setDescription(category.getDescription());

        return newCategory;
    }

    public static Item dtoToItem(AddItemDto itemDto) {
        Item newItem = new Item();
        newItem.setTitle(itemDto.getTitle());
        newItem.setPrice(itemDto.getPrice());
        newItem.setDescription(itemDto.getDescription());
        newItem.setCategory(categoryRepository
                .findById(itemDto.getCategoryId())
                .orElseThrow());
        newItem.setCreated(LocalDateTime.now());
        newItem.setUserProfile(userProfileRepository
                .findById(itemDto.getUserId())
                .orElseThrow());

        return newItem;
    }
}
