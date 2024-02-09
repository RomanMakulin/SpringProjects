package com.example.ClientAPI.controller;

import com.example.ClientAPI.dto.card.ActionMoneyDetails;
import com.example.ClientAPI.dto.card.CardUpdateDetails;
import com.example.ClientAPI.dto.card.TransferDetails;
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

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style.css";
    }

    @GetMapping
    public String getUserProfile(Model model){
        model.addAttribute("user", usersTransferService.getAllUsers().get(0));
        return "user";
    }

    @GetMapping("/update-name/{id}")
    public String getUpdateForm(User user){
        return "update-name.html";
    }

    @GetMapping("/receive/{id}")
    public String receiveMoney(ActionMoneyDetails actionMoneyDetails, User user){
        return "receive";
    }

    @GetMapping("/withdraw/{id}")
    public String withdrawMoney(ActionMoneyDetails actionMoneyDetails, User user){
        return "withdraw";
    }

    @GetMapping("/transfer/{id}")
    public String transferMoney(TransferDetails transferDetails, User user){
        return "transfer";
    }

    @GetMapping("/changePin/{id}")
    public String transferMoney(CardUpdateDetails cardUpdateDetails, User user){
        return "changePin";
    }
}
