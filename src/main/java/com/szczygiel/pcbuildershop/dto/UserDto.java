package com.szczygiel.pcbuildershop.dto;

import com.szczygiel.pcbuildershop.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Field 'username' is mandatory.")
    @Size(min = 8, max = 32, message = "Field 'username' should be minimum 8 chars and maximum 32 chars.")
    private String username;

    private List<Item> items;
}
