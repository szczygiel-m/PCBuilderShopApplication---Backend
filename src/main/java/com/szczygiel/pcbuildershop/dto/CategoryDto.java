package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CategoryDto {

    @Size(min = 4, max = 32, message = "Field 'category' should be minimum 4 chars and maximum 32 chars.")
    private final String category;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private final String description;
}
