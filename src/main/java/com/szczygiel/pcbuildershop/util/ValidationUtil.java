package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.dto.AddItemDto;
import com.szczygiel.pcbuildershop.repository.CategoryRepository;
import com.szczygiel.pcbuildershop.repository.UserProfileRepository;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidationUtil {

    private final CategoryRepository categoryRepository;
    private final UserProfileRepository userProfileRepository;

    public ValidationUtil(CategoryRepository categoryRepository, UserProfileRepository userProfileRepository) {
        this.categoryRepository = categoryRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public String getErrorsMessages(Errors errors) {
        List<String> errorsMessages = errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return String.join(". ", errorsMessages);
    }

    public String validItemDto(AddItemDto itemDto) {
        String response = "";

        if(!isUserExisting(itemDto.getUserId())) {
            response += "Invalid userId. ";
        }
        if(!isCategoryExisting(itemDto.getCategoryId())) {
            response += "Invalid categoryId. ";
        }
        if(!isTitleValid(itemDto.getTitle())) {
            response += "Invalid title. ";
        }
        if(!isDescriptionValid(itemDto.getDescription())) {
            response += "Invalid description";
        }

        return response;
    }

    public boolean isUserExisting(Long userId){
        return userProfileRepository.existsById(userId);
    }

    public boolean isCategoryExisting(Long categoryId){
        return categoryRepository.existsById(categoryId);
    }

    public boolean isTitleValid(String title){
        return title.length() <= 50;
    }

    public boolean isDescriptionValid(String description) {
        return description.length() <= 250;
    }

    public int validatePage(int page) {
        if(page < 1) {
            page = 1;
        }

        return page - 1;
    }

    public int validateSize(int size) {
        if(size < 0) {
            size = 0;
        }

        return size;
    }
}