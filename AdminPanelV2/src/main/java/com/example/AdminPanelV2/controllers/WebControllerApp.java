package com.example.AdminPanelV2.controllers;

import com.example.AdminPanelV2.config.CustomUserDetails;
import com.example.AdminPanelV2.models.User;
import com.example.AdminPanelV2.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WebControllerApp {
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/loginUser")
    public String getUserLoginPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        if (userDetails.getUser().getRole().name().equals("ROLE_ADMIN")) return "redirect:/admin";
        return "redirect:/user";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("users", customUserDetailsService.getAllUsers());
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("user", userDetails.getUser());

        return "user";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(User user) {
        return "registration";
    }

    @PostMapping("/registration-user")
    public String registrationUser(User user) {
        customUserDetailsService.createUser(user);
        System.out.println(user.getUsername());
        return getIndex();
    }

    @GetMapping("/user-update/{id}")
    public String getUserUpdateForm(User user){
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        customUserDetailsService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}") // доделать
    public String deleteUser(User user){
        customUserDetailsService.deleteUser(user.getId());
        return "redirect:/admin";
    }

}
