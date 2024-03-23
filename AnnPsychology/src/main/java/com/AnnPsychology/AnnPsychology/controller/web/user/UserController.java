package com.AnnPsychology.AnnPsychology.controller.web.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.user.iUserDetailsService;
import com.AnnPsychology.AnnPsychology.services.user.iUserSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер управления пользователем
 */
@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    /**
     * Интерфейс управления пользователем
     */
    private final iUserDetailsService userDetailsService;

    /**
     * Подключение стилей
     *
     * @return css
     */
    @GetMapping("/css/**")
    public String css() {
        return "../static/css/user.css";
    }

    /**
     * Форма редактирования пользователя
     *
     * @param model модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping("/edit")
    public String editUserForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        return "user/edit-user.html";
    }

    /**
     * Запрос на редактирование пользователя
     *
     * @param id          уникальгный ID пользователя из url
     * @param userDetails данные для обновления пользователя из frontend
     * @return главная страница пользователя
     */
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, User userDetails) {
        userDetailsService.updateUser(id, userDetails);
        return "redirect:/user";
    }
}
