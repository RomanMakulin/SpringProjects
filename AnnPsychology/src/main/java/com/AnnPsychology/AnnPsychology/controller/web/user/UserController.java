package com.AnnPsychology.AnnPsychology.controller.web.user;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.user.iUserDetailsService;
import com.AnnPsychology.AnnPsychology.services.user.iUserSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final iUserDetailsService userDetailsService;
    private final iUserSessionService userSessionService;

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/user.css";
    }

    @GetMapping("/edit")
    public String editUserForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        return "user/edit-user.html";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, User user) {
        userDetailsService.updateUser(id, user);
        return "redirect:/user";
    }

    @GetMapping("/hw/{id}")
    public String getHomeWork(@PathVariable("id") Long sessionId, Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("hw", userSessionService.getUserHomework(sessionId));
        model.addAttribute("sessionItem", userSessionService.getSessionById(sessionId, userSessionService.getAdapterRepository()));
        return "user/user-hw.html";
    }

}
