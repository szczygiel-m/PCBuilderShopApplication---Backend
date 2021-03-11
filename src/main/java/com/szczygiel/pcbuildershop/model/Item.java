package com.szczygiel.pcbuildershop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "Field 'category' is mandatory")
    @ManyToOne
    private Category category;

    @ManyToOne
    private UserProfile userProfile;

    @NotBlank(message = "Field 'itemTitle' is mandatory")
    private String title;

    private String description;

    @NotBlank(message = "Field 'created' should be valid")
    private LocalDateTime created;

    @Positive(message = "Field 'price' should be positive")
    private BigDecimal price;
}
