package com.example.sem3HomeTask.controllers.WEB;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.TaskServiceImpl;
import com.example.sem3HomeTask.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TasksController {
    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/users-filter")
    public String filterUsersDB(Model model, @RequestParam("age") int age){
        List<User> users = taskService.filterUsersByAge(userService.getAllUsers(), age);
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users-sort")
    public String sortUsersDB(Model model){
        List<User> users = taskService.sortUsersByAge(userService.getAllUsers());
        model.addAttribute("users", users);
        return "user-list";
    }
}
