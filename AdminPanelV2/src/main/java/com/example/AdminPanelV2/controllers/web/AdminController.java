package com.example.AdminPanelV2.controllers.web;

import com.example.AdminPanelV2.config.CustomUserDetails;
import com.example.AdminPanelV2.models.User;
import com.example.AdminPanelV2.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Запрос на получение страницы администратора
     *
     * @param model связь с шаблонизатором
     * @return html страница администратора
     */
    @GetMapping
    public String getAdminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        // Обновляем данные о пользователе
        customUserDetailsService.loadUserByUsername(userDetails.getUsername());
        User user = customUserDetailsService.getById(userDetails.getUser().getId()).orElseThrow();

        model.addAttribute("user", user);
        model.addAttribute("users", customUserDetailsService.getAllUsers());
        return "admin";
    }

    /**
     * Запрос на получение формы для обновления пользователя
     *
     * @param model связь с шаблонизатором
     * @param id    уникальный идентификатор пользователя
     * @return html страница-форма обновления пользователя
     */
    @GetMapping("/user-update/{id}")
    public String getUserUpdateForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", customUserDetailsService.getById(id));
        return "user-update";
    }

    /**
     * Запрос на обновление пользователя
     *
     * @param user данные для обновления пользователя
     * @return переадресация на гл. страницу администратора
     */
    @PostMapping("/user-update")
    public String updateUser(User user) {
        customUserDetailsService.updateUser(user);
        return "redirect:/admin";
    }

    /**
     * Запрос на удаление пользователей из БД
     * Исключением является текущий пользователь. Его удалить нельзя
     *
     * @param id передаваемый идентификатор пользователя для удаления
     * @return возвращаемся на страницу администратора
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // Проверка. Администратор не может удалить себя
        if (!Objects.equals(userDetails.getUser().getId(), id)) customUserDetailsService.deleteUser(id);
        return "redirect:/admin";
    }
}
