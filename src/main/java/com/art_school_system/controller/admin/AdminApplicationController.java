package com.art_school_system.controller.admin;

import com.art_school_system.model.Application;
import com.art_school_system.model.ApplicationStatus;
import com.art_school_system.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/applications")
@RequiredArgsConstructor
public class AdminApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public String applicationsList(Model model) {
        model.addAttribute("applications", applicationService.findAll());
        return "admin/applications/list";
    }

    @GetMapping("/view/{id}")
    public String viewApplication(@PathVariable Long id, Model model) {
        Application application = applicationService.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        model.addAttribute("app", application);
        model.addAttribute("statuses", ApplicationStatus.values());
        return "admin/applications/view";
    }

    @PostMapping("/update/{id}")
    public String updateApplication(@PathVariable Long id, 
                                    @RequestParam ApplicationStatus status,
                                    @RequestParam(required = false) String adminComment) {
        Application application = applicationService.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        application.setAdminComment(adminComment);
        application.setProcessedAt(LocalDateTime.now());
        applicationService.save(application);
        return "redirect:/admin/applications";
    }

    @PostMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteById(id);
        return "redirect:/admin/applications";
    }
}