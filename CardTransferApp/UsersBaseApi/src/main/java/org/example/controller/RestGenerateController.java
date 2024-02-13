package org.example.controller;

import lombok.Data;
import org.example.models.User;
import org.example.service.UserGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
public class RestGenerateController {
    /**
     * Сервис управления генерацией пользователей
     */
    @Autowired
    private UserGenerateService userGenerateService;

    /**
     * Запрос на генерацию списка рандомных пользователей
     *
     * @return список пользователей
     */
    @GetMapping("/load-users")
    public List<User> generate() {
        return userGenerateService.loadUsers();
    }
}
