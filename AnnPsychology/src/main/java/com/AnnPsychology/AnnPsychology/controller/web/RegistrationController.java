package com.AnnPsychology.AnnPsychology.controller.web;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.UserPageService.CustomUserDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/registration")
public class RegistrationController {

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public String getForm(User user){
        return "/registration/registration.html";
    }

    @PostMapping("/registration-user")
    public String registration(User user){
        customUserDetailsService.createUser(user);
        return "redirect:/registration";
    }


    @GetMapping("/css/**")
    public String css() {
        return "../static/css/reg.css";
    }

}
