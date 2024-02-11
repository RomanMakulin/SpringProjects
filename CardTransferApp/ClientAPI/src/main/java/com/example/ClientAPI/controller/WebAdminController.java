package com.example.ClientAPI.controller;

//import com.example.ClientAPI.services.UsersTransferService;
import com.example.ClientAPI.models.User;
import com.example.ClientAPI.services.UserService;
import feign.Feign;
import feign.slf4j.Slf4jLogger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main/admin")
public class WebAdminController {
    /**
     * Сервис управления внешними данными
     */
    @Autowired
    private UserService userService;

    /**
     * Страница просмотра всех пользователей и их редактирования
     * TO DO: Добавить функционал редактирования пользователя
     *
     * @param model связь с шаблонизатором
     * @return страница просмотра всех пользователей
     */
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

}
