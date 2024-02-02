package com.example.AdminPanelV2.controllers;

import com.example.AdminPanelV2.models.User;
import com.example.AdminPanelV2.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestControllerApp {
    /**
     * Кастомный
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Тестовый запрос для добавления нового пользователя (регистрация)
     *
     * @param user пользователь из frontend
     * @return созданный пользователь + статус выполнения
     */
    @PostMapping("/api/create-user")
    public ResponseEntity<User> createNew(@RequestBody User user) {
        return new ResponseEntity<User>(customUserDetailsService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Создание администратора
     *
     * @param user регитрационные данные для администратора из запроса
     * @return созданный пользователь
     */
    @PostMapping("/api/create-admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        return new ResponseEntity<User>(customUserDetailsService
                .creteAdmin(
                        user.getUsername(),
                        user.getEmail(),
                        user.getAge(),
                        "123"),
                HttpStatus.CREATED);
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<User>(customUserDetailsService.getById(id).orElseThrow(), HttpStatus.OK);
    }

    @DeleteMapping("/api/{id}")
    public void delUserById(@PathVariable("id") Long id){
        customUserDetailsService.deleteUser(id);
    }
}
