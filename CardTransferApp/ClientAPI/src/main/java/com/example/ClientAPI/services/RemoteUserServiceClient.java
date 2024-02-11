package com.example.ClientAPI.services;

import com.example.ClientAPI.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "http://127.0.0.1:8765", configuration = MyClientConfiguration.class, value = "jplaceholder")
public interface RemoteUserServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/server")
    List<User> getAllUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/server/{id}")
    User getSingleUser(@PathVariable("id") Long id);
}
