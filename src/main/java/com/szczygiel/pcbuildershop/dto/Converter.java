package com.szczygiel.pcbuildershop.dto;

import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import com.szczygiel.pcbuildershop.repository.UserProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Item dtoToItem(ItemDto itemDto) {
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

    public static ItemDto itemToDto(Item item) {
        return new ItemDto(item.getId(),
                item.getCategory().getId(),
                item.getUserProfile().getId(),
                item.getCreated(),
                item.getTitle(),
                item.getDescription(),
                item.getPrice());
    }

    public static Page<ItemDto> itemPageToDto(Page<Item> allItems) {
        List<ItemDto> itemsListed = allItems.stream().map(Converter::itemToDto).collect(Collectors.toList());

        return new PageImpl<>(itemsListed);
    }
}
