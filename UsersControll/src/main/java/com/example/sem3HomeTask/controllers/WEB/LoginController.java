package com.example.sem3HomeTask.controllers.WEB;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.tasks.TaskServiceImpl;
import com.example.sem3HomeTask.services.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Log
@Controller
public class LoginController {
    /**
     * Сервис реализации логики управления пользователями
     */
    private UserServiceImpl userService;

    /**
     * Открытие главной страницы входа
     *
     * @return
     */
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    /**
     * Обработка входа пользователя в систему
     *
     * @param email    почта из браузера
     * @param password пароль из браузера
     * @return если авторизация успешна - откроется админ анель, если нет - страница ошибки.
     */
    @GetMapping("/check-login")
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password) {

        try {
            User user = userService.getAllUsers().stream()
                    .filter(item -> (item.getEmail().equals("sup.makulin@mail.ru")
                            && item.getPassword().equals("12s"))).findFirst().get();

            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return "redirect:/users-db";
            } else return "404";
        } catch (Exception e) {
            return "404";
        }
    }
}
