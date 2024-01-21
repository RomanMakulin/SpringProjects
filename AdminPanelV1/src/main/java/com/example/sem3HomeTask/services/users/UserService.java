package com.example.sem3HomeTask.services.users;

import com.example.sem3HomeTask.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void delUser(int id);
}
