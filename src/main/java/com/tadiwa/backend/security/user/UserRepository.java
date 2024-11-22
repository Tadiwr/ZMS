package com.tadiwa.backend.security.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tadiwa.backend.security.user_role.UserRole;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByUserRole(UserRole role);
}
