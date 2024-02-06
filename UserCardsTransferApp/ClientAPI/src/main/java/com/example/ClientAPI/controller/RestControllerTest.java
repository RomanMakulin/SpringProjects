package com.example.ClientAPI.controller;

import com.example.ClientAPI.models.User;
import com.example.ClientAPI.services.UsersTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер создан с целью проверки работоспособности подключения к своему внешнему серверу с данными
 */
@RestController
public class RestControllerTest {
    /**
     * Сервис управления внешними данными
     */
    @Autowired
    private UsersTransferService usersTransferService;

    /**
     * Запрос получения всех users
     *
     * @return статус выполнения запроса
     */
    @GetMapping("/api")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(usersTransferService.getAllUsers(), HttpStatus.OK);
    }

}
