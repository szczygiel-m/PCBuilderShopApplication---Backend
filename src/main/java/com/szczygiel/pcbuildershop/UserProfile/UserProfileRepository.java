package com.szczygiel.pcbuildershop.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUsername(String userName);

    boolean existsByEmail(String email);

    boolean existsByUsername(String userProfile);
}
