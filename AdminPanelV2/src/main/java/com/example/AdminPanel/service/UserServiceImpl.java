package com.example.AdminPanel.service;

import com.example.AdminPanel.model.Role;
import com.example.AdminPanel.model.User;
import com.example.AdminPanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<User> getByRole(Role role) {
        return userRepository.getByRole(role);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void create(User userDetails) {
        if (Objects.isNull(getByUsername(userDetails.getUsername()))){
            User user = createByParams(userDetails.getUsername(), "{noop}" + userDetails.getPassword(), Role.ROLE_USER);
            new InMemoryUserDetailsManager(user);
        }

    }


    public User createByParams(String name, String password, Role role){
        User user = new User(name, password, role);
        userRepository.save(user);
        return user;
    }

    public void starterPack(){
        createByParams("Roman", "{noop}12345678", Role.ROLE_ADMIN);
        createByParams("Olga", "{noop}123", Role.ROLE_USER);
    }
}
