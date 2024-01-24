package com.example.Scrum.board.services;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import com.example.Scrum.board.models.User;
import com.example.Scrum.board.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskRepositoryService {
    private final TaskRepository taskRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public void create(Task task, User user){
        task.setUser(user);
        taskRepository.save(task);
//        taskRepository.createTask(task.getTitle(), task.getDescription(), task.getTaskStatus(), id);
    }

    public List<Task> getByTitle(String title){
        return taskRepository.getByTitle(title);
    }

    public List<Task> getByStatus(TaskStatus taskStatus){
        return taskRepository.getByTaskStatus(taskStatus);
    }

    public void delTask(Task task){
        taskRepository.delete(task);
    }

    public Task getById(int id){
        return taskRepository.getById(id);
    }

    public Task updateTask(Task task){
        return taskRepository.updateTask(task.getTitle(), task.getDescription(), task.getTaskStatus(), task.getId());
    }

}
