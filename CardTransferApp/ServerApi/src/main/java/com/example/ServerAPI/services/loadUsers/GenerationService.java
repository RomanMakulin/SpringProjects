package com.example.ServerAPI.services.loadUsers;

import com.example.ServerAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationService {

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
    public List<User> loadUsers() {

        return remoteUserServiceClient.getAllUsers();
    }
}
