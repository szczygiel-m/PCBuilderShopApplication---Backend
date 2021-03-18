package com.szczygiel.pcbuildershop.repository;

import com.szczygiel.pcbuildershop.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUsername(String userName);

    boolean existsByEmail(String email);

    boolean existsByUsername(String userProfile);
}
