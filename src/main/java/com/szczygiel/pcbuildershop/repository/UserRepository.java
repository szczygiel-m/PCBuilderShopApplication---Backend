package com.szczygiel.pcbuildershop.repository;

import com.szczygiel.pcbuildershop.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserProfile, Long> {

}
