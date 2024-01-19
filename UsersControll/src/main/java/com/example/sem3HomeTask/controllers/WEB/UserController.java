package com.example.sem3HomeTask.controllers.WEB;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users-db")
    public String findAllDB(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String getCreateFormDB(User user){
        return "user-create.html";
    }

    @PostMapping("/user-create")
    public String createUserDB(User user){
        userService.createUser(user);
        return "redirect:/users-db";
    }

    @GetMapping("/user-update/{id}")
    public String updateForm(User user){
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users-db";
    }

    @GetMapping("/user-delete/{id}")
    public String delUser(@PathVariable("id") int id){
        userService.delUser(id);
        return "redirect:/users-db";
    }

}
