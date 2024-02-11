package com.example.ClientAPI.services;

import com.example.ClientAPI.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Определение клиента OpenFeign
 */
@FeignClient(url = "http://127.0.0.1:8765", configuration = MyClientConfiguration.class, value = "jplaceholder")
public interface RemoteUserServiceClient {

    /**
     * Метод получения всех пользователей
     *
     * @return список пользователей в сериализованном виде
     */
    @RequestMapping(method = RequestMethod.GET, value = "/server")
    List<User> getAllUsers();

    /**
     * Метод получения пользователя по ID
     *
     * @param id уникальный идентификатор
     * @return пользователь в сериализованном виде
     */
    @RequestMapping(method = RequestMethod.GET, value = "/server/{id}")
    User getSingleUser(@PathVariable("id") Long id);
}
