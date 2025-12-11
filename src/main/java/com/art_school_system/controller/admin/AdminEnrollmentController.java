package com.art_school_system.controller.admin;

import com.art_school_system.model.Enrollment;
import com.art_school_system.model.EnrollmentStatus;
import com.art_school_system.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/enrollments")
@RequiredArgsConstructor
public class AdminEnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public String enrollmentsList(Model model) {
        model.addAttribute("enrollments", enrollmentService.findAll());
        return "admin/enrollments/list";
    }

    @GetMapping("/view/{id}")
    public String viewEnrollment(@PathVariable Long id, Model model) {
        Enrollment enrollment = enrollmentService.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("statuses", EnrollmentStatus.values());
        return "admin/enrollments/view";
    }

    @PostMapping("/update/{id}")
    public String updateEnrollment(@PathVariable Long id, 
                                   @RequestParam EnrollmentStatus status,
                                   @RequestParam(required = false) String comment) {
        Enrollment enrollment = enrollmentService.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setStatus(status);
        enrollment.setComment(comment);
        enrollment.setProcessedAt(LocalDateTime.now());
        enrollmentService.save(enrollment);
        return "redirect:/admin/enrollments";
    }

    @PostMapping("/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return "redirect:/admin/enrollments";
    }
}