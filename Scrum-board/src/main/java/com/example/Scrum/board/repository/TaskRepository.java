package com.example.Scrum.board.repository;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//    @Modifying
//    @Transactional
//    @Query("INSERT INTO tasks VALUES (DEFAULT, title = :title, description = :description, taskStatus = :taskStatus, user = :user)")
//    void createTask(String title, String description, TaskStatus taskStatus, int user);

    List<Task> getByTitle(String title);

    List<Task> getByTaskStatus(TaskStatus taskStatus);

    Task getById(int id);

    @Query("UPDATE tasks SET title=:title, description=:description, taskStatus=:taskStatus WHERE id=:id")
    Task updateTask(String title, String description, TaskStatus taskStatus, int id);

}