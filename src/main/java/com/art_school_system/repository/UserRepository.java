package com.art_school_system.repository;

import com.art_school_system.model.Role;
import com.art_school_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByRole(Role role);
    boolean existsByEmail(String email);
}