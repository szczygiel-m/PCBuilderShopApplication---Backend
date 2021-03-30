package com.szczygiel.pcbuildershop.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUsername(String userName);

    boolean existsByEmail(String email);

    boolean existsByUsername(String userProfile);

    Optional<UserProfile> findOneByUsername(String username);
}
