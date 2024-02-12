package com.example.ServerAPI.controllers;


import com.example.ServerAPI.dto.user.UserCreateDetails;
import com.example.ServerAPI.dto.user.UserUpdateDetails;
import com.example.ServerAPI.models.User;
import com.example.ServerAPI.services.UserServiceImpl;
import com.example.ServerAPI.services.loadUsers.GenerationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/server")
public class UserController {
    /**
     * Управление пользователями в БД
     */
    private final UserServiceImpl userService;

    /**
     * Класс для получения рандомных пользователей по внешнему API
     */
    private GenerationService generationService;

    /**
     * Получеие всех пользователей из БД
     *
     * @return список пользователей
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
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
    
    @PostMapping("/updateName/{id}")
    public RedirectView updateUsernameById(@PathVariable("id") Long id, User userDetails) {
        userService.updateNameUser(userDetails, id);
        return new RedirectView("http://localhost:8765/main/user");
    }

    /**
     * Получение пользователя по ID
     * @param id уникальный идентификатор
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getById(id).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/load")
    public ResponseEntity<Void> loadUsersToDB(){
        userService.loadUsers();
        return ResponseEntity.ok().build();
    }

}
