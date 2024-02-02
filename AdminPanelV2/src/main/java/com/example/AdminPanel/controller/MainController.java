package com.example.AdminPanel.controller;

import com.example.AdminPanel.model.User;
import com.example.AdminPanel.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Data
public class MainController {

    private final UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/registration")
    public String getFormRegistration(User user){
        return "registration";
    }

    @PostMapping("/registration-user")
    public String registrationUser(Model model, User user){
        model.addAttribute("user", user);
        userService.create(user);
        return index(model);
    }

//    @GetMapping("/user")
//    public String user(Model model) {
//        model.addAttribute("text", userService.getText());
//        return "user";
//    }
//
//    @GetMapping("/admin")
//    public String admin(Model model) {
//        model.addAttribute("text", adminService.getText());
//        return "admin";
//    }
}
