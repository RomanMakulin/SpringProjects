package com.example.sem3HomeTask.controllers.REST;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.TaskServiceImpl;
import com.example.sem3HomeTask.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class RestUserController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * Метод добавления пользователя с фронта в наш репозиторий
     * @param user пользователь полученный из тела запроса
     */
    @PutMapping("/create") // при создании прописывать айди
    public void restCreateUser(@RequestBody User user){
        userService.createUser(user);
    }

    /**
     * Get запрос для выведения общего списка пользователей (зарег)
     * localhost:8080/user
     * @return список зарегистрированных пользователей
     */
    @GetMapping
    public List<User> userList() {
        return userService.getAllUsers();
    }

    @PostMapping("/create-param")
    public String registrationParam(@RequestParam("name") String name,
                                    @RequestParam("age") int age,
                                    @RequestParam("email") String email) {

        userService.createUserParam(name, age, email);
        return "User added from param request!";
    }
}
