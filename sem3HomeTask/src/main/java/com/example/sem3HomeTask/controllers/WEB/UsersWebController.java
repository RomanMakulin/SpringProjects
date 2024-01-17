package com.example.sem3HomeTask.controllers.WEB;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersWebController {
    @Autowired
    private TasksService tasksService;

    @GetMapping("/users-db")
    public String findAllDB(Model model){
        List<User> users = tasksService.getDataBaseService().findAllFromDB();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String getCreateFormDB(User user){
        return "user-create.html";
    }

    @PostMapping("/user-create")
    public String createUserDB(User user){
        tasksService.createUserDB(user);
        return "redirect:/users-db";
    }

    @PostMapping("/users-filter")
    public String filterUsersDB(Model model, @RequestParam("age") int age){
        List<User> users = tasksService.getDataBaseService().filterByAgeDB(age);
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users-sort")
    public String sortUsersDB(Model model){
        List<User> users = tasksService.getDataBaseService().sortByAgeDB();
        model.addAttribute("users", users);
        return "user-list";
    }

}
