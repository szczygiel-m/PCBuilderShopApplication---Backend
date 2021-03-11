package com.szczygiel.pcbuildershop.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ItemDto {

    private final Long Id;
    private final UserProfileDto userProfile;
    private final String title;
    private final String description;
    private final LocalDateTime created;
    private final BigDecimal price;
}
