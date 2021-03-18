package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.dto.CategoryDto;
import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;
import com.szczygiel.pcbuildershop.model.Category;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.model.UserProfile;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import com.szczygiel.pcbuildershop.repository.UserProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    
    private final CategoryRepository categoryRepository;
    private final UserProfileRepository userProfileRepository;

    public DtoConverter(CategoryRepository categoryRepository, UserProfileRepository userProfileRepository) {
        this.categoryRepository = categoryRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile registerDtoToUser(RegisterDto registerDto) {
        UserProfile newUser = new UserProfile();
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(registerDto.getPassword());
        newUser.setUsername(registerDto.getUsername());

        return newUser;
    }

    public Category categoryDtoToCategory(CategoryDto category) {
        Category newCategory = new Category();
        newCategory.setCategory(category.getCategory());
        newCategory.setDescription(category.getDescription());

        return newCategory;
    }

    public Item itemDtoToItem(ItemDto itemDto) {
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

    public ItemDto itemToItemDto(Item item) {
        return new ItemDto(item.getId(),
                item.getCategory().getId(),
                item.getUserProfile().getId(),
                item.getCreated(),
                item.getTitle(),
                item.getDescription(),
                item.getPrice());
    }

    public Page<ItemDto> itemPageCollectionToItemDtoCollection(Page<Item> allItems) {
        List<ItemDto> itemsListed = allItems.stream().map(this::itemToItemDto).collect(Collectors.toList());

        return new PageImpl<>(itemsListed);
    }
}
