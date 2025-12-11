package com.art_school_system.service;

import com.art_school_system.model.Direction;
import com.art_school_system.repository.DirectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectionService {

    private final DirectionRepository directionRepository;

    @Transactional(readOnly = true)
    public List<Direction> findAll() {
        return directionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Direction> findById(Long id) {
        return directionRepository.findById(id);
    }

    @Transactional
    public Direction save(Direction direction) {
        return directionRepository.save(direction);
    }

    @Transactional
    public void deleteById(Long id) {
        directionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return directionRepository.count();
    }
}