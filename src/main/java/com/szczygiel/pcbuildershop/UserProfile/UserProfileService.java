package com.szczygiel.pcbuildershop.UserProfile;

import com.szczygiel.pcbuildershop.util.DtoConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService implements UserDetailsService {

    private final UserProfileRepository userProfileRepository;
    private final DtoConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserProfileService(UserProfileRepository userProfileRepository, DtoConverter converter, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> getUserProfile(Long userId){
        Optional<UserProfile> user = userProfileRepository.findById(userId);

        return user.map(userProfile -> new UserDto(userProfile.getUsername(), userProfile.getItems()));
    }

    public String login(LoginDto loginDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginDto.getUsername());
        if(userProfile != null){
            if(userProfile.getPassword().equals(loginDto.getPassword())) {
                return "Successfully logged in!";
            }
        }
        return "Invalid Field 'username' or 'password'.";
    }

    public UserProfile registerUser(RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return userProfileRepository.save(converter.registerDtoToUser(registerDto));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));
    }
}
