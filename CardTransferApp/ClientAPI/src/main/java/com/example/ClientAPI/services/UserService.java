package com.example.ClientAPI.services;

import com.example.ClientAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RemoteUserServiceClient remoteUserServiceClient;

    public List<User> getAllUsers() {
        return remoteUserServiceClient.getAllUsers();
    }

    public User getSingleUser(Long id) {
        return remoteUserServiceClient.getSingleUser(id);
    }
}
