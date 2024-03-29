package com.example.ServerAPI.services;

import com.example.ServerAPI.dto.user.UserCreateDetails;
import com.example.ServerAPI.dto.user.UserUpdateDetails;
import com.example.ServerAPI.models.User;

import java.util.List;
import java.util.Optional;

public interface iUserService {
    User createNew(UserCreateDetails userCreateDetails);
    void deleteUser(Long id);
    User updateUser(UserUpdateDetails userUpdateDetails, Long id);
    User updateNameUser(User userDetails, Long id);
    List<User> getAllUsers();
    void loadUsers();
    Optional<User> getById(Long id);
    User updateUser(User user);
}
