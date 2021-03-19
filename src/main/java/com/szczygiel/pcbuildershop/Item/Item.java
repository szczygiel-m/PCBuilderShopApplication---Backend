package com.szczygiel.pcbuildershop.Item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.szczygiel.pcbuildershop.Category.Category;
import com.szczygiel.pcbuildershop.UserProfile.UserProfile;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private UserProfile userProfile;

    @Size(min = 4, max = 32, message = "Field 'category' should be minimum 4 chars and maximum 32 chars.")
    private String title;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private String description;

    private LocalDateTime created;

    @PositiveOrZero(message = "Field 'price' should be positive or zero.")
    private BigDecimal price;
}
