package com.AnnPsychology.AnnPsychology.controller.web.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.admin.iAdminUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class UsersManageController {
    private final iAdminUserService adminUserService;

    @GetMapping("/all-users")
    public String allUsers(Model model) {
        model.addAttribute("users", adminUserService.getAllUsers());
        return "admin/admin_users.html";
    }

    @PostMapping("/change-price/{id}")
    public String changePrice(@PathVariable("id") Long id, User user) {
        adminUserService.changePriceUser(id, user.getPrice());
        return "redirect:/admin/all-users";
    }

    @GetMapping("/hw-history/{id}")
    public String getUserHomeworkList(Model model, @PathVariable("id") Long userId) {
        User user = adminUserService.getUserByID(userId);
        List<Session> sessionList = user.getSessionList().stream().filter(i -> i.getSessionHomework() != null).toList();

        model.addAttribute("user", user);
        model.addAttribute("userHomeworks", sessionList);

        return "admin/admin_hw_history.html";
    }
}
