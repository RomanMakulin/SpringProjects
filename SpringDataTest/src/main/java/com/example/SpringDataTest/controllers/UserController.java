package com.example.SpringDataTest.controllers;

import com.example.SpringDataTest.models.User;
import com.example.SpringDataTest.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Data
@AllArgsConstructor
@Controller
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/create-user")
    public String createForm(User user){
        return "create-user";
    }

    @PostMapping("/create-user")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/";
    }
}
