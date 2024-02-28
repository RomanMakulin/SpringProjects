package com.AnnPsychology.AnnPsychology.controller.web;

import com.AnnPsychology.AnnPsychology.config.CustomUserDetails;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.SessionService;
import com.AnnPsychology.AnnPsychology.services.CustomUserDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
//    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final SessionService sessionService;

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/user.css";
    }

    @GetMapping
    public String allUsers(Model model){
        model.addAttribute("user", getAuthUser());
        return "/user/user.html";
    }

    @GetMapping("/edit")
    public String editForm(Model model){
        model.addAttribute("user", getAuthUser());
        return "/user/edit-user.html";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, User user){
        customUserDetailsService.updateUser(id, user);
        return "redirect:/user";
    }

    @GetMapping("/sign-up-session")
    public String signUpSessionForm(Model model){
        model.addAttribute("user", getAuthUser());
        return "/user/go-session.html";
    }

    @PostMapping("/sign-up-session/{id}")
    public String signUpSession(@PathVariable("id") Long id,
                                @ModelAttribute("date") LocalDate date,
                                @ModelAttribute("time") LocalTime time){
        boolean result = sessionService.signUpSession(id, date, time);
        return (result) ? "redirect:/user" : "redirect:/user/sign-up-session";
    }


    /**
     * Отменить сессию и вернуть деньги
     * @param id уникальный идентификатор пользователя
     * @return личный кабинет пользователя
     */
    @GetMapping("/cancel/{id}")
    public String cancelSession(@PathVariable("id") Long id){
        boolean result = sessionService.cancelSession(id);
        return (result) ? "redirect:/user" : "redirect:/user/sign-up-session";
    }

//    optional
    public User getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        customUserDetailsService.loadUserByUsername(userDetails.getEmail());
        return customUserDetailsService.getById(userDetails.getUser().getId());
    }
}
