package com.art_school_system.controller.site;

import com.art_school_system.model.Application;
import com.art_school_system.model.ApplicationStatus;
import com.art_school_system.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("applicationForm", new Application());
        return "site/applications/form";
    }

    @PostMapping("/create")
    public String createApplication(@ModelAttribute("applicationForm") Application applicationForm) {
        applicationForm.setStatus(ApplicationStatus.NEW);
        applicationForm.setCreatedAt(LocalDateTime.now());
        applicationService.save(applicationForm);
        return "redirect:/?applicationSent=true";
    }
}