package com.szczygiel.pcbuildershop.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.szczygiel.pcbuildershop.Item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(min = 8, max = 32, message = "Field 'category' should be minimum 8 chars and maximum 32 chars.")
    @NotBlank(message = "Field 'category' is be mandatory.")
    private String category;

    @Size(max = 250, message = "Field 'description' should be max 250 chars.")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> items;
}
