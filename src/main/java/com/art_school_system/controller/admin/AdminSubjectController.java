package com.art_school_system.controller.admin;

import com.art_school_system.model.Role;
import com.art_school_system.model.Subject;
import com.art_school_system.model.User;
import com.art_school_system.service.DirectionService;
import com.art_school_system.service.SubjectService;
import com.art_school_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/subjects")
@RequiredArgsConstructor
public class AdminSubjectController {

    private final SubjectService subjectService;
    private final DirectionService directionService;
    private final UserService userService;

    @GetMapping
    public String subjectsList(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/subjects/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("directions", directionService.findAll());
        List<User> teachers = userService.findAll().stream()
                .filter(user -> user.getRole() == Role.TEACHER)
                .toList();
        model.addAttribute("teachers", teachers);
        return "admin/subjects/form";
    }

    @PostMapping("/create")
    public String createSubject(@ModelAttribute("subjectForm") Subject subjectForm) {
        subjectService.save(subjectForm);
        return "redirect:/admin/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));
        model.addAttribute("subjectForm", subject);
        model.addAttribute("directions", directionService.findAll());
        List<User> teachers = userService.findAll().stream()
                .filter(user -> user.getRole() == Role.TEACHER)
                .toList();
        model.addAttribute("teachers", teachers);
        return "admin/subjects/form";
    }

    @PostMapping("/edit/{id}")
    public String updateSubject(@PathVariable Long id, @ModelAttribute("subjectForm") Subject subjectForm) {
        subjectForm.setId(id);
        subjectService.save(subjectForm);
        return "redirect:/admin/subjects";
    }

    @PostMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteById(id);
        return "redirect:/admin/subjects";
    }
}