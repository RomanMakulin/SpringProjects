package com.example.AdminPanel.service;

import com.example.AdminPanel.model.Role;
import com.example.AdminPanel.model.User;

import java.util.List;

public interface UserService {
    List<User> getByRole(Role role);
    public List<User> getAll();

    public User getByUsername(String username);

    public void create (User user);

    public User createByParams(String name, String password, Role role);

    public void starterPack();
}
