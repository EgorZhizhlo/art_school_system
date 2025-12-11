package com.art_school_system.controller.admin;

import com.art_school_system.service.UserService;
import com.art_school_system.service.DirectionService;
import com.art_school_system.service.SubjectService;
import com.art_school_system.service.NewsService;
import com.art_school_system.service.EnrollmentService;
import com.art_school_system.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final DirectionService directionService;
    private final SubjectService subjectService;
    private final NewsService newsService;
    private final EnrollmentService enrollmentService;
    private final ApplicationService applicationService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("usersCount", userService.countAll());
        model.addAttribute("directionsCount", directionService.countAll());
        model.addAttribute("subjectsCount", subjectService.countAll());
        model.addAttribute("newsCount", newsService.countAll());
        model.addAttribute("enrollmentsCount", enrollmentService.countAll());
        model.addAttribute("applicationsCount", applicationService.countAll());
        return "admin/dashboard";
    }
}
