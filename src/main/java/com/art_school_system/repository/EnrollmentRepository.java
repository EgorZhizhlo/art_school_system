package com.art_school_system.repository;

import com.art_school_system.model.Enrollment;
import com.art_school_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
    List<Enrollment> findByUserOrderByCreatedAtDesc(User user);
}