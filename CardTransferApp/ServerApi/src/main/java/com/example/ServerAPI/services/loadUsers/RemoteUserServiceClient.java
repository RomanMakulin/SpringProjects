package com.example.ServerAPI.services.loadUsers;

import com.example.ServerAPI.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "http://127.0.0.1:8765", configuration = MyClientConfiguration.class, value = "generate")
public interface RemoteUserServiceClient {
    /**
     * Метод получения всех пользователей
     *
     * @return список пользователей в сериализованном виде
     */
    @RequestMapping(method = RequestMethod.GET, value = "/load-users")
    List<User> getAllUsers();
}
