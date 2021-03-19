package com.szczygiel.pcbuildershop.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ItemSearchRequest {

    private Long categoryId = null;

    private Long userId = null;

    @NotNull
    @Size(max = 32, message = "Field 'title' should be maximum 32 chars.")
    private String phrase = "";

    @NotBlank
    @PositiveOrZero(message = "Field 'page' should be positive or zero.")
    private int page = 0;

    @NotBlank
    @PositiveOrZero(message = "Field 'page' should be positivee or zero.")
    private int size = 10;

    private String sortParam = "created";

    private String sortDirection = "ASC";
}
