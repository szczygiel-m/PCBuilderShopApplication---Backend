package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.UserProfileDto;
import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<ItemDto> getItemsByCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow()
                .getItems()
                .stream()
                .map(this::itemToItemDto)
                .collect(Collectors.toList());
    }

    private ItemDto itemToItemDto(Item item) {
        return ItemDto.builder()
                .Id(item.getId())
                .description(item.getDescription())
                .title(item.getTitle())
                .price(item.getPrice())
                .userProfile(userProfileToDto(item.getUserProfile()))
                .created(item.getCreated()).build();
    }

    private UserProfileDto userProfileToDto(UserProfile userProfile) {
        return UserProfileDto.builder()
                .Id(userProfile.getId())
                .email(userProfile.getEmail())
                .password(userProfile.getPassword())
                .username(userProfile.getUsername())
                .build();
    }
}
