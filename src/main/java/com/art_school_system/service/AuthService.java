package com.art_school_system.service;

import com.art_school_system.model.User;
import com.art_school_system.repository.UserRepository;
import com.art_school_system.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        
        if (principal instanceof CustomUserDetails) {
            return Optional.of(((CustomUserDetails) principal).getUser());
        }
        
        return Optional.empty();
    }
}
