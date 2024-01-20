package com.example.sem3HomeTask.controllers.REST;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.tasks.TaskServiceImpl;
import com.example.sem3HomeTask.services.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class RestUserController {

    /**
     * Сервис реализации логики задач
     */
    private TaskServiceImpl taskService;

    /**
     * Сервис реализации логики управления пользователями
     */
    private UserServiceImpl userService;

    /**
     * Метод добавления пользователя с фронта в наш репозиторий
     *
     * @param user пользователь полученный из тела запроса
     */
    @PutMapping("/create") // при создании прописывать айди
    public void restCreateUser(@RequestBody User user) {
        userService.createUser(user);
    }

    /**
     * Get запрос для выведения общего списка пользователей (зарег)
     * localhost:8080/user
     *
     * @return список зарегистрированных пользователей
     */
    @GetMapping
    public List<User> userList() {
        return userService.getAllUsers();
    }

    /**
     * Метод регистрации пользователя через параметры в адресной строке
     *
     * @param name  имя пользователя
     * @param age   возраст пользователя
     * @param email почта пользователя
     * @return возвращает уведомлегние об успешном создании
     */
    @PostMapping("/create-param")
    public String registrationParam(@RequestParam("name") String name,
                                    @RequestParam("age") int age,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password) {

        userService.createUserParam(name, age, email, password);
        return "User added from param request!";
    }
}
