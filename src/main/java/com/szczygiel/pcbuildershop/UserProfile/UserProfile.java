package com.szczygiel.pcbuildershop.UserProfile;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.szczygiel.pcbuildershop.Item.Item;
import com.szczygiel.pcbuildershop.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Field 'username' is mandatory.")
    @Size(min = 4, max = 32, message = "Field 'username' should be minimum 4 chars and maximum 32 chars.")
    private String username;

    @NotBlank(message = "Field 'password' is mandatory.")
    @Size(min = 8, message = "Field 'password' should be minimum 8 chars.")
    @Column(length = 100)
    private String password;

    private ApplicationUserRole role = ApplicationUserRole.USER;

    @NotBlank(message = "Field 'email' is mandatory.")
    @Email(message = "Field 'email' should be valid.")
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<Item> items;

    private boolean isLocked;

    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return !isEnabled;
    }
}
