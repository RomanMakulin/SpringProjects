package com.example.ClientAPI.controller;

import com.example.ClientAPI.models.User;
import com.example.ClientAPI.services.UsersTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * Контроллер создан с целью проверки работоспособности подключения к своему внешнему серверу с данными
 */
@Controller
@RequestMapping("/main/user")
public class WebUserController extends SpringBootServletInitializer {
    /**
     * Сервис управления внешними данными
     */
    @Autowired
    private UsersTransferService usersTransferService;

    @GetMapping
    public String getUserProfile(Model model){
        model.addAttribute("user", usersTransferService.getAllUsers().get(0));
        return "user";
    }

    @GetMapping("/update-name/{id}") // delete id
    public String getUpdateForm(User user){
        return "update-name.html";
    }

    // recieve 
    // создать dto (скопировать с сервера)
    @GetMapping("/recieve")
    public String recieveMoney(User user){ // ActionMoneyDetails actionMoneyDetails 
        return "";
    }

    // withdraw
    @GetMapping("/withdraw")
    public String withdrawMoney(User user){
        return "";
    }

    // transfer

    // changePin

}
