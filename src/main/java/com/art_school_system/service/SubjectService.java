package com.art_school_system.service;

import com.art_school_system.model.Subject;
import com.art_school_system.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Transactional
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return subjectRepository.count();
    }
}