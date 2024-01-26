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
    List<Task> getByTitle(String title);

    List<Task> getByTaskStatus(TaskStatus taskStatus);

    Task getById(int id);

    @Modifying
    @Transactional
    @Query("UPDATE tasks SET title=:title, description=:description, taskStatus=:taskStatus WHERE id=:id")
    void updateTask(String title, String description, TaskStatus taskStatus, int id);

}