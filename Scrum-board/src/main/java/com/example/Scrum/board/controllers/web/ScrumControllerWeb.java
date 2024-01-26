package com.example.Scrum.board.controllers.web;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import com.example.Scrum.board.services.TaskRepositoryService;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ScrumControllerWeb {
    private final UserRepositoryService userRepositoryService;
    private final TaskRepositoryService taskRepositoryService;

    @GetMapping("/main")
    public String getScrum(Model model) {
        model.addAttribute("users", userRepositoryService.getAll());
        return "index";
    }

    @GetMapping("/task-doing/{id}")
    public String turnToDoing(Model model, @PathVariable("id") int id) {
        taskRepositoryService.updateStatusById(id, TaskStatus.DOING);
        return getScrum(model);
    }

    @GetMapping("/task-done/{id}")
    public String turnToDone(Model model, @PathVariable("id") int id) {
        taskRepositoryService.updateStatusById(id, TaskStatus.DONE);
        return getScrum(model);
    }

    @GetMapping("/task-todo/{id}")
    public String turnToToDo(Model model, @PathVariable("id") int id) {
        taskRepositoryService.updateStatusById(id, TaskStatus.TO_DO);
        return getScrum(model);
    }

    @GetMapping("/task-delete/{id}")
    public String turnToDelete(Model model, @PathVariable("id") int id) {
        taskRepositoryService.delTask(id);
        return getScrum(model);
    }

    @GetMapping("/edit-task/{task}")
    public String formUpdateTask(@PathVariable("task") Task task) {
        return "edit-task";
    }

    @PostMapping("/edit-task/{id}")
    public String updateTask(Task task, @PathVariable("id") int id) {
        taskRepositoryService.updateTask(id, task.getTitle(), task.getDescription());
        return "redirect:/main";
    }
}
