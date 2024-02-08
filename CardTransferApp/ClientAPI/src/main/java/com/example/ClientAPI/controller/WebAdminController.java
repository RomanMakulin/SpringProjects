package com.example.ClientAPI.controller;

import com.example.ClientAPI.services.UsersTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/admin")
public class WebAdminController {
    /**
     * Сервис управления внешними данными
     */
    @Autowired
    private UsersTransferService usersTransferService;

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", usersTransferService.getAllUsers());
        return "admin";
    }
}
