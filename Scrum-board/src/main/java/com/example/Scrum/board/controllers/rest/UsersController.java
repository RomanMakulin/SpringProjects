package com.example.Scrum.board.controllers.rest;


import com.example.Scrum.board.models.User;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST запросы для юзеров
 */
@RestController
@RequiredArgsConstructor
public class UsersController {
    /**
     * Управление пользователями
     */
    private final UserRepositoryService userService;

    /**
     * Показать всех пользователей
     *
     * @return статус выполнения
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    /**
     * Создание нового пользователя
     *
     * @param user пользователь из body rest запроса
     * @return статус выполнения
     */
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Обновить пользователя
     *
     * @param user пользователь из body rest запроса
     * @return статус выполнения
     */
    @PostMapping("/update-user")
    public String updateUser(@RequestBody User user) {
        userService.update(user);
        return "complete!";
    }

    /**
     * Удалить пользователя
     *
     * @param user пользователь из body rest запроса
     * @return статус выполнения
     */
    @PostMapping("/delete-user")
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return "complete!";
    }
}
