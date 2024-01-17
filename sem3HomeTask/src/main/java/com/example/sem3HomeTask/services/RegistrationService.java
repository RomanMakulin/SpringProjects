package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private TasksService tasksService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    public TasksService getTasksService() {
        return tasksService;
    }

    public void setTasksService(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Регистрация пользователя с использованием параметров в запросе
     */
    public void processRegistration(String name, int age, String email){
        User user = userService.createUser(name, age, email);
        tasksService.addUserToList(user);
        notificationService.sendNotification("User: " + user + "\nRegistration success!");
    }
}
