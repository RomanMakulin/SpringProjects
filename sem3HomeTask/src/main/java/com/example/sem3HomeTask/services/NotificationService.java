package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    /**
     * Уведомление о создании нового пользователя
     * @param user пользователь из запроса
     */
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getFirstName());
    }

    /**
     * Управляемое уведомление
     * @param s текст уведомления
     */
    public void sendNotification(String s) {
        System.out.println(s);
    }
}
