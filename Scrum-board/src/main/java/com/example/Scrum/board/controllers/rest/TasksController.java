package com.example.Scrum.board.controllers.rest;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.services.TaskRepositoryService;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST запросы для задач
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {
    /**
     * Управление задачами
     */
    private final TaskRepositoryService taskService;
    /**
     * Управление пользователями
     */
    private final UserRepositoryService userService;

    /**
     * Показать всех пользователей
     *
     * @return Список пользователей
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    /**
     * Дать задачу пользователю
     *
     * @param task задача, передаваемая в body rest запроса
     * @param id   id пользователя которому дать задачу
     * @return статус выполнения запроса
     */
    @PostMapping("/give/{id}")
    public String giveTask(@RequestBody Task task, @PathVariable("id") int id) {
        taskService.create(task, userService.getById(id));
        return "complete!";
    }

}
