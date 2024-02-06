package com.example.UsersCardTransfer.controllers;

import com.example.UsersCardTransfer.dto.user.UserCreateDetails;
import com.example.UsersCardTransfer.dto.user.UserUpdateDetails;
import com.example.UsersCardTransfer.models.User;
import com.example.UsersCardTransfer.services.UserServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    /**
     * Управление пользователями в БД
     */
    private final UserServiceImpl userService;

    /**
     * Получеие всех пользователей из БД
     *
     * @return список пользователей
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
//        userService.getUserRepository().save(new User("Roman"));
//        userService.getUserRepository().save(new User("Olga"));
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Создание нового пользователя в БД
     *
     * @param userCreateDetails данные из тела запроса JSON
     * @return новый пользователь
     */
    @PutMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDetails userCreateDetails) {
        return new ResponseEntity<>(userService.createNew(userCreateDetails), HttpStatus.CREATED);
    }

    /**
     * Удаление пользователя по ID
     *
     * @param id уникальный идентификатор
     * @return результат выполнения запроса
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Обновление пользователя в БД
     *
     * @param id уникальынй идентификатор
     * @param userUpdateDetails данные из тела запроса JSON
     * @return обновленный пользователь
     */
    @PostMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDetails userUpdateDetails) {
        return new ResponseEntity<>(userService.updateUser(userUpdateDetails, id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getById(id).orElseThrow(), HttpStatus.OK);
    }
}
