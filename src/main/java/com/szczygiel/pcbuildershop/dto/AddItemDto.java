package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AddItemDto {

    @NotBlank(message = "Field 'categoryId' is mandatory.")
    private final Long categoryId;

    @NotBlank(message = "Field 'categoryId' is mandatory.")
    private final Long userId;

    @Size(min = 4, max = 32, message = "Field 'category' should be minimum 4 chars and maximum 32 chars.")
    private final String title;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private final String description;

    @PositiveOrZero(message = "Field 'price' should be positive or zero.")
    private BigDecimal price;
}
