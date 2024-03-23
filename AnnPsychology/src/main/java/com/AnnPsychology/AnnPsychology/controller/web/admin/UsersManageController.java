package com.AnnPsychology.AnnPsychology.controller.web.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.admin.iAdminUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер администратора (пользователи)
 */
@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class UsersManageController {

    /**
     * Интерфейс управления пользователями
     */
    private final iAdminUserService adminUserService;

    /**
     * Запрос на получение списка всех пользователей
     *
     * @param model модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping("/all-users")
    public String allUsers(Model model) {
        model.addAttribute("users", adminUserService.getAllUsers());
        return "admin/admin_users.html";
    }

    /**
     * Запрос на изменение цены пользователю за сессии
     *
     * @param id          ID пользователя
     * @param userDetails обновленные данные (цена)
     * @return html списка всех пользователей
     */
    @PostMapping("/change-price/{id}")
    public String changePrice(@PathVariable("id") Long id, User userDetails) {
        adminUserService.changePriceUser(id, userDetails.getPrice());
        return "redirect:/admin/all-users";
    }

    /**
     * Запрос на получение истории ДЗ у конкретного пользователя
     *
     * @param model  модель взаимодействия с шаблонизатором
     * @param userId ID пользователя
     * @return html с историей ДЗ
     */
    @GetMapping("/hw-history/{id}")
    public String getUserHomeworkList(Model model, @PathVariable("id") Long userId) {
        User user = adminUserService.getUserByID(userId);
        List<Session> sessionList = user.getSessionList().stream().filter(i -> i.getSessionHomework() != null).toList();

        model.addAttribute("user", user);
        model.addAttribute("userHomeworks", sessionList);

        return "admin/admin_hw_history.html";
    }
}
