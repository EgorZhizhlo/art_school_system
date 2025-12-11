package com.art_school_system.service;

import com.art_school_system.model.Application;
import com.art_school_system.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    @Transactional
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Transactional
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return applicationRepository.count();
    }
}