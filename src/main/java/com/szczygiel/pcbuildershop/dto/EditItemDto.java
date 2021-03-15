package com.szczygiel.pcbuildershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class EditItemDto {

    @Id
    private Long Id;

    @Size(min = 4, max = 32, message = "Field 'category' should be minimum 4 chars and maximum 32 chars.")
    private String title;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private String description;

    @PositiveOrZero(message = "Field 'price' should be positive or zero.")
    private BigDecimal price;
}
