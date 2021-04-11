package com.szczygiel.pcbuildershop.UserProfile;

import com.szczygiel.pcbuildershop.jwt.JwtConfig;
import com.szczygiel.pcbuildershop.util.DtoConverter;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class UserProfileService implements UserDetailsService {

    private final UserProfileRepository userProfileRepository;
    private final DtoConverter converter;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public UserProfileService(UserProfileRepository userProfileRepository, DtoConverter converter, PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userProfileRepository = userProfileRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    public Optional<UserDto> getUserProfile(Long userId) {
        Optional<UserProfile> user = userProfileRepository.findById(userId);

        return user.map(userProfile -> new UserDto(userProfile.getUsername(), userProfile.getItems()));
    }

    public String login(LoginDto loginDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginDto.getUsername());
        if (userProfile != null) {
            if (passwordEncoder.matches(loginDto.getPassword(), userProfile.getPassword())) {
                long expirationTime = jwtConfig.getTokenExpirationAfterHours() * 60 * 60 * 1000;

                return "Bearer " + Jwts.builder()
                        .setSubject(userProfile.getUsername())
                        .claim("authorities", userProfile.getAuthorities())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                        .signWith(secretKey)
                        .compact();
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
