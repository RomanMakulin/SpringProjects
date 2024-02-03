package com.example.AdminPanelV2.controllers.web;

import com.example.AdminPanelV2.config.CustomUserDetails;
import com.example.AdminPanelV2.models.User;
import com.example.AdminPanelV2.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Главная страница
     *
     * @return html главной страницы
     */
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    /**
     * Настройка переадресации после успешного входа
     *
     * @return страница пользователя
     */
    @GetMapping("/loginUser")
    public String getUserLoginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        if (userDetails.getUser().getRole().name().equals("ROLE_ADMIN")) return "redirect:/admin";
        return "redirect:/user";
    }

    /**
     * Запрос на получение формы регистрации пользователя
     *
     * @param user новый пользователь для деталей
     * @return форма регистрации html
     */
    @GetMapping("/registration")
    public String getRegistrationForm(User user) {
        return "registration";
    }

    /**
     * Функционал регистрации пользователя после наполнения от frontend
     *
     * @param user заполлненный пользователь
     * @return переадрессация на главную страницу
     */
    @PostMapping("/registration-user")
    public String registrationUser(User user) {
        customUserDetailsService.createUser(user);
        System.out.println(user.getUsername());
        return getIndex();
    }
}
