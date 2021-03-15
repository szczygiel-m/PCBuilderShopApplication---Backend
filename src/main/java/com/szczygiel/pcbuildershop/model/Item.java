package com.szczygiel.pcbuildershop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JsonBackReference
    private UserProfile userProfile;

    @Size(min = 4, max = 32, message = "Field 'category' should be minimum 4 chars and maximum 32 chars.")
    private String title;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private String description;

    @NotBlank(message = "Field 'created' should be valid")
    private LocalDateTime created;

    @PositiveOrZero(message = "Field 'price' should be positive or zero.")
    private BigDecimal price;
}
