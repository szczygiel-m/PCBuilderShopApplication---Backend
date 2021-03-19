package com.szczygiel.pcbuildershop.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ItemDto {

    private final Long id;

    @NotBlank(message = "Field 'categoryId' is mandatory.")
    private final Long categoryId;

    @NotBlank(message = "Field 'categoryId' is mandatory.")
    private final Long userId;

    @NotBlank(message = "Field 'created' should be valid")
    private LocalDateTime created;

    @Size(min = 4, max = 32, message = "Field 'title' should be minimum 4 chars and maximum 32 chars.")
    private String title;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private String description;

    @PositiveOrZero(message = "Field 'price' should be positive or zero.")
    private BigDecimal price;
}
