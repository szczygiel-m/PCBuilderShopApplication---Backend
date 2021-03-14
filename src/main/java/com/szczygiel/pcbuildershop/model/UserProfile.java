package com.szczygiel.pcbuildershop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "Field 'username' is mandatory")
    @Size(min = 8, max = 32, message = "Field 'username' should be longer than 8 chars and shorter than 32")
    private String username;

    @NotBlank(message = "Field 'password' is mandatory")
    @Size(min = 8, max = 32, message = "Field 'password' should be longer than 8 chars and shorter than 32")
    private String password;

    @NotNull
    @Email(message = "Field 'email' should be valid")
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Item> items;
}
