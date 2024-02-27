package com.AnnPsychology.AnnPsychology.controller.web;

import com.AnnPsychology.AnnPsychology.config.CustomUserDetails;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.UserRole;
import com.AnnPsychology.AnnPsychology.services.CustomUserDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Data
public class RegistrationController {

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/welcome")
    public String successLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        if(userDetails.getRole().equals("ROLE_ADMIN")) return "redirect:/admin";
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login(){
        return "/registration/login";
    }

    @GetMapping("/registration")
    public String getForm(User user){
        return "/registration/registration.html";
    }

    @PostMapping("/registration-user")
    public String registration(User user){
        customUserDetailsService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/registration/css/**")
    public String css() {
        return "../static/css/reg.css";
    }

}
