package com.example.sem3HomeTask.controllers.REST;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegistrationService service;

    /**
     * Get запрос для выведения общего списка пользователей (зарег)
     * localhost:8080/user
     * @return список зарегистрированных пользователей
     */
    @GetMapping
    public List<User> userList() {
            return service.getTasksService().getRepository().getUsers();
    }

    /**
     * Метод добавления пользователя с фронта в наш репозиторий
     * @param user пользователь полученный из тела запроса
     * @return строковое сообщение об успешном добавлении пользователя
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getTasksService().getRepository().getUsers().add(user);
        return "User added from body!";
    }

    @PostMapping("/create-param")
    public String registrationParam(@RequestParam("name") String name,
                                    @RequestParam("age") int age,
                                    @RequestParam("email") String email) {

        service.processRegistration(name, age, email);
        return "User added from param request!";
    }
}
