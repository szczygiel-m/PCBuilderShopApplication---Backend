package com.szczygiel.pcbuildershop.UserProfile;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.szczygiel.pcbuildershop.Item.Item;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Field 'username' is mandatory.")
    @Size(min = 4, max = 32, message = "Field 'username' should be minimum 4 chars and maximum 32 chars.")
    private String username;

    @NotBlank(message = "Field 'password' is mandatory.")
    @Size(min = 8, max = 32, message = "Field 'password' should be minimum 8 chars and maximum 32 chars.")
    private String password;

    @NotBlank(message = "Field 'email' is mandatory.")
    @Email(message = "Field 'email' should be valid.")
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<Item> items;
}
