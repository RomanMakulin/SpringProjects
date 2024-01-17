package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private NotificationService notificationService;

    /**
     * Создание пользователя
     * @param name имя пользователя
     * @param age возраст пользователя
     * @param email почта пользователя
     * @return возрвщаем нового пользователя
     */
    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setFirstName(name);
        user.setAge(age);
        user.setEmail(email);

        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);

        return user;
    }
}
