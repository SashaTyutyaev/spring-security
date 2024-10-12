package com.vladdosiik.security.controller;

import com.vladdosiik.security.model.dto.UserPostAdminDto;
import com.vladdosiik.security.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("user", new UserPostAdminDto());
        return "admin";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserPostAdminDto user,
                             RedirectAttributes redirectAttributes) {
        try {
            adminService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "User created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating user: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/setRole")
    public String setRoleToUser(@RequestParam("username") String name,
                                @RequestParam("roleName") String role,
                                RedirectAttributes redirectAttributes) {
        try {
            adminService.setRoleToUser(name, role);
            redirectAttributes.addFlashAttribute("message", "Role assigned successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error assigning role: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("name") String name,
                             RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteUser(name);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting user: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
