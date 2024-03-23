package com.AnnPsychology.AnnPsychology.controller.web.user;

import com.AnnPsychology.AnnPsychology.config.CustomUserDetails;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.user.iUserDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер регистрации пользователя
 */
@Controller
@RequiredArgsConstructor
@Data
public class RegistrationController {

    /**
     * Сервис управления пользователями
     */
    private final iUserDetailsService userDetailsService;

    /**
     * Страница - прокси для редиректа после успешного логирования
     *
     * @return страница личного кабинета
     */
    @GetMapping("/welcome")
    public String successLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        if (userDetails.getRole().equals("ROLE_ADMIN")) return "redirect:/admin";
        return "redirect:/user";
    }

    /**
     * Страница входа в Личный Кабинет
     *
     * @return html
     */
    @GetMapping("/login")
    public String login() {
        return "registration/login";
    }

    /**
     * Страница регистрации в Личный Кабинет (форма)
     *
     * @return html
     */
    @GetMapping("/registration")
    public String getForm(User user) {
        return "registration/registration.html";
    }

    /**
     * Выполнение логики регистрации
     *
     * @return true: редирект на страницу входа, false: html ошибки
     */
    @PostMapping("/registration-user")
    public String registration(User user, Model model) {
        model.addAttribute("errorMessage", "Данный email уже занят другим пользователем");
        return userDetailsService.createUser(user) ? "redirect:/login" : "registration/registration-error.html";
    }

    /**
     * Подключение файла стилей
     *
     * @return css
     */
    @GetMapping("/registration/css/**")
    public String css() {
        return "../static/css/reg.css";
    }

}
