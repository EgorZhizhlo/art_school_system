package com.art_school_system.controller.admin;

import com.art_school_system.model.Direction;
import com.art_school_system.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/directions")
@RequiredArgsConstructor
public class AdminDirectionController {

    private final DirectionService directionService;

    @GetMapping
    public String directionsList(Model model) {
        model.addAttribute("directions", directionService.findAll());
        return "admin/directions/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("directionForm", new Direction());
        return "admin/directions/form";
    }

    @PostMapping("/create")
    public String createDirection(@ModelAttribute("directionForm") Direction directionForm) {
        directionService.save(directionForm);
        return "redirect:/admin/directions";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Direction direction = directionService.findById(id).orElseThrow(() -> new RuntimeException("Direction not found"));
        model.addAttribute("directionForm", direction);
        return "admin/directions/form";
    }

    @PostMapping("/edit/{id}")
    public String updateDirection(@PathVariable Long id, @ModelAttribute("directionForm") Direction directionForm) {
        directionForm.setId(id);
        directionService.save(directionForm);
        return "redirect:/admin/directions";
    }

    @PostMapping("/delete/{id}")
    public String deleteDirection(@PathVariable Long id) {
        directionService.deleteById(id);
        return "redirect:/admin/directions";
    }
}