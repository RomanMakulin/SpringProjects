package com.example.Scrum.board.services;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import com.example.Scrum.board.models.User;
import com.example.Scrum.board.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskRepositoryService {
    private final TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public void create(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    public List<Task> getByTitle(String title) {
        return taskRepository.getByTitle(title);
    }

    public List<Task> getByStatus(TaskStatus taskStatus) {
        return taskRepository.getByTaskStatus(taskStatus);
    }

    public void delTask(Task task) {
        taskRepository.delete(task);
    }

    public void delTask(int id) {
        taskRepository.delete(getById(id));
    }

    public Task getById(int id) {
        return taskRepository.getById(id);
    }

    public void updateTask(Task task) {
        taskRepository.updateTask(task.getTitle(), task.getDescription(), task.getTaskStatus(), task.getId());
    }

    public void updateTask(int id, String title, String desc) {
        Task task = taskRepository.getById(id);
        if (task != null) {
            task.setTitle(title);
            task.setDescription(desc);
            taskRepository.save(task);
        } else {
            throw new RuntimeException("error");
        }
    }

    public void updateStatusById(int id, TaskStatus taskStatus) {
        Task task = getById(id);
        task.setTaskStatus(taskStatus);
        updateTask(task);
    }

}
