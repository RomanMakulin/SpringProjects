package com.example.LoginProfile.services.db;

import com.example.LoginProfile.models.User;

import java.util.List;

public interface UsersManageService {
    List<User> getAll();
    User getUserByID();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

}
