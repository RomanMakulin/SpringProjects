package com.AnnPsychology.AnnPsychology.controller.web;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.services.admin.AdminAdapter;
import com.AnnPsychology.AnnPsychology.services.admin.AdminSessionService;
import com.AnnPsychology.AnnPsychology.services.SessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class AdminController {
    //    private final AdminSessionService adminService;
    private final SessionService sessionService;
    private final AdminAdapter adminService;

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style_admin.css";
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("sessions", adminService.getAdminSessionService().getAllSessions());
        return "/admin/admin.html";
    }

    @GetMapping("/all-users")
    public String allUsers(Model model) {
        model.addAttribute("users", adminService.getAdminUserService().getAllUsers());
        return "/admin/admin_users.html";
    }

    @PostMapping("/change-price/{id}")
    public String changePrice(@PathVariable("id") Long id, User user) {
        adminService.getAdminUserService().changePriceUser(id, user.getPrice());
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
        adminService.getAdminSessionService().editSessionDateByAdmin(id, date, time);
        return "redirect:/admin";
    }

    @PostMapping("/change-link/{id}")
    public String changeLink(@PathVariable("id") Long id,
                             @ModelAttribute("sessionLink") String link) {

        adminService.getAdminSessionService().giveSessionLink(id, link);
        return "redirect:/admin";
    }

    @GetMapping("/cancel/{id}")
    public String cancelSession(@PathVariable("id") Long id){
        adminService.getAdminSessionService().cancelSession(id);
        return "redirect:/admin";
    }

    @GetMapping("/latest")
    public String latest(Model model){
        model.addAttribute("latestSession", adminService.getAdminSessionService().getLatest());
        return "/admin/admin_latest.html";
    }

    @PostMapping("/give-hw/{id}")
    public String giveHomework(@PathVariable("id") Long id,
                             @ModelAttribute("sessionHomework") String sessionHomework) {

        adminService.getAdminSessionService().giveSessionHomeWork(id, sessionHomework);
        return "redirect:/admin";
    }

}
