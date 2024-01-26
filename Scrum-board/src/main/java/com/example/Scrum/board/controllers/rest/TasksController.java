package com.example.Scrum.board.controllers.rest;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.services.TaskRepositoryService;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {
    private final TaskRepositoryService taskService;
    private final UserRepositoryService userService;
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAll();
    }

    @PostMapping("/give/{id}")
    public String giveTask(@RequestBody Task task, @PathVariable("id") int id){
        taskService.create(task, userService.getById(id));
        return "complete!";
    }

}
