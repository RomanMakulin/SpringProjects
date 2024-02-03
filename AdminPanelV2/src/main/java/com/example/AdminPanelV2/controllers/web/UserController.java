package com.example.AdminPanelV2.controllers.web;

import com.example.AdminPanelV2.config.CustomUserDetails;
import com.example.AdminPanelV2.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Запрос на получение страницы пользователя
     *
     * @param model связь с шаблонизатором
     * @return html страница пользователя - личный кабинет
     */
    @GetMapping("/user")
    public String getUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("user", userDetails.getUser());

        return "user";
    }
}
