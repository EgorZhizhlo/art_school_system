package com.art_school_system.controller.site;

import com.art_school_system.model.Enrollment;
import com.art_school_system.model.EnrollmentStatus;
import com.art_school_system.service.AuthService;
import com.art_school_system.service.EnrollmentService;
import com.art_school_system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final SubjectService subjectService;
    private final AuthService authService;

    @GetMapping
    public String myEnrollments(Model model) {
        model.addAttribute("enrollments", enrollmentService.findByUser(authService.getCurrentUser().orElseThrow()));
        return "site/enrollments/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("enrollmentForm", new Enrollment());
        model.addAttribute("subjects", subjectService.findAll());
        return "site/enrollments/form";
    }

    @PostMapping("/create")
    public String createEnrollment(@ModelAttribute("enrollmentForm") Enrollment enrollmentForm) {
        enrollmentForm.setUser(authService.getCurrentUser().orElseThrow());
        enrollmentForm.setStatus(EnrollmentStatus.PENDING);
        enrollmentForm.setCreatedAt(LocalDateTime.now());
        enrollmentService.save(enrollmentForm);
        return "redirect:/enrollments?success=true";
    }
}