package com.AnnPsychology.AnnPsychology.controller.web;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.AdminServiceImpl;
import com.AnnPsychology.AnnPsychology.services.SessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    public String adminPage(Model model) {
        model.addAttribute("sessions", adminService.getAllSessions());
        return "/admin/admin.html";
    }

    @GetMapping("/all-users")
    public String allUsers(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "/admin/admin_users.html";
    }

    @PostMapping("/change-price/{id}")
    public String changePrice(@PathVariable("id") Long id, User user) {
        adminService.changePrice(id, user.getPrice());
        return "redirect:/admin/all-users";
    }

    @GetMapping("/edit-session-form/{id}")
    public String editForm(Model model,
                           @PathVariable("id") Long id) {
        model.addAttribute("sess", sessionService.getById(id));
        model.addAttribute("user", sessionService.getUserBySessionId(id));

        return "/admin/admin_user_update.html";
    }

    @PostMapping("/edit-session/{id}")
    public String editSession(@PathVariable("id") Long id,
                              @ModelAttribute("date") LocalDate date,
                              @ModelAttribute("time") LocalTime time,
                              @ModelAttribute("link") String link) {
        sessionService.editSessionDateByAdmin(id, date, time);
        return "redirect:/admin";
    }

    @PostMapping("/change-link/{id}")
    public String changeLink(Model model,
                             @PathVariable("id") Long id,
                             @ModelAttribute("sessionLink") String link) {

        sessionService.giveSessionLink(id, link);

        return "redirect:/admin";
    }

}
