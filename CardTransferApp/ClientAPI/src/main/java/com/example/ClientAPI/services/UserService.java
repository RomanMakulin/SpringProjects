package com.example.ClientAPI.services;

import com.example.ClientAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для получения данных по внешнему API
 */
@Service
public class UserService {

    /**
     * Получаем интерфейс получения данных через OpenFeign
     */
    @Autowired
    private RemoteUserServiceClient remoteUserServiceClient;

    /**
     * Метод получения всех пользователей
     *
     * @return список пользователей в сериализованном виде
     */
    public List<User> getAllUsers() {
        return remoteUserServiceClient.getAllUsers();
    }

    /**
     * Метод получения пользователя по ID
     *
     * @param id уникальный идентификатор
     * @return пользователь в сериализованном виде
     */
    public User getSingleUser(Long id) {
        return remoteUserServiceClient.getSingleUser(id);
    }
}
