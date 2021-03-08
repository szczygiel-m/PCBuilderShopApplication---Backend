package com.szczygiel.pcbuildershop.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @ManyToOne
    private Category category;

    @ManyToOne
    private UserProfile userProfileId;

    @NotNull
    private String itemTitle;
    private String itemDescriptiopn;
}
