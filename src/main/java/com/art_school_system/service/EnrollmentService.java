package com.art_school_system.service;

import com.art_school_system.model.Enrollment;
import com.art_school_system.model.User;
import com.art_school_system.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional(readOnly = true)
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findByUser(User user) {
        return enrollmentRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Transactional
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return enrollmentRepository.count();
    }
}