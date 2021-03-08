package com.szczygiel.pcbuildershop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String category;

    private String description;
}
