package com.example.Scrum.board.controllers.rest;


import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.User;
import com.example.Scrum.board.services.TaskRepositoryService;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserRepositoryService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/update-user")
    public String updateUser(@RequestBody User user){
        userService.update(user);
        return "complete!";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return "complete!";
    }
}
