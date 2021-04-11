package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.Item.ItemDto;
import com.szczygiel.pcbuildershop.Item.ItemSearchRequest;
import com.szczygiel.pcbuildershop.UserProfile.RegisterDto;
import com.szczygiel.pcbuildershop.UserProfile.UserProfileRepository;
import com.szczygiel.pcbuildershop.category.CategoryRepository;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Sort;
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

    public String validateItemDto(ItemDto itemDto) {
        String response = "";

        if (!isUserExisting(itemDto.getUserId())) {
            response += "Invalid userId. ";
        }
        if (!isCategoryExisting(itemDto.getCategoryId())) {
            response += "Invalid categoryId. ";
        }
        if (!isTitleValid(itemDto.getTitle())) {
            response += "Invalid title. ";
        }
        if (!isDescriptionValid(itemDto.getDescription())) {
            response += "Invalid description.";
        }

        return response;
    }

    public boolean isUserExisting(Long userId) {
        return userProfileRepository.existsById(userId);
    }

    public boolean isCategoryExisting(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    public boolean isTitleValid(String title) {
        return title.length() <= 50;
    }

    public boolean isDescriptionValid(String description) {
        return description.length() <= 250;
    }

    public int validatePage(int page) {
        if (page < 1) {
            page = 1;
        }

        return page - 1;
    }

    public int validateSize(int size) {
        if (size < 0) {
            size = 0;
        }

        return size;
    }

    public boolean isRegisterDtoValid(RegisterDto userProfile) {
        if (userProfile.getUsername() == null || userProfile.getEmail() == null || userProfile.getPassword() == null) {
            return false;
        }
        return !isUsernameExists(userProfile.getUsername()) && !isEmailUsed(userProfile.getEmail()) && isRegisterRequestValid(userProfile);
    }

    private boolean isRegisterRequestValid(RegisterDto userProfile) {
        return userProfile.getUsername().length() >= 4 &&
                userProfile.getUsername().length() <= 32 &&
                userProfile.getPassword().length() >= 8;
    }

    public boolean isUsernameExists(String userProfile) {
        return userProfileRepository.existsByUsername(userProfile);
    }

    public boolean isEmailUsed(String email) {
        return userProfileRepository.existsByEmail(email);
    }

    public ItemSearchRequest.SortParamEnum validateSortParam(ItemSearchRequest.SortParamEnum sortParam) {
        if (sortParam.equals(ItemSearchRequest.SortParamEnum.PRICE) || sortParam.equals(ItemSearchRequest.SortParamEnum.CREATED))
            return sortParam;
        return ItemSearchRequest.SortParamEnum.CREATED;
    }

    public Sort.Direction validateSortDirection(Sort.Direction sortDirection) {
        if (sortDirection.equals(Sort.Direction.ASC) || sortDirection.equals(Sort.Direction.DESC))
            return sortDirection;
        return Sort.Direction.ASC;
    }
}
