package com.AnnPsychology.AnnPsychology.controller.web;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.AdminServiceImpl;
import com.AnnPsychology.AnnPsychology.services.SessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImpl adminService;
    private final SessionService sessionService;

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style_admin.css";
    }

    @GetMapping
    public String adminPage(Model model){
        model.addAttribute("sessions", adminService.getAllSessions());
        return "/admin/admin.html";
    }

    @GetMapping("/all-users")
    public String allUsers(Model model){
        model.addAttribute("users", adminService.getAllUsers());
        return "/admin/admin_users.html";
    }

    @PostMapping("/change-price/{id}")
    public String changePrice(@PathVariable("id") Long id, User user){
        adminService.changePrice(id, user.getPrice());
        return "redirect:/admin/all-users";
    }
}
