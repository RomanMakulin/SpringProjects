package com.AnnPsychology.AnnPsychology.services;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    /**
     * Интерфейс взаимодействия с Базой Данных
     * Таблица: users
     */
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User userDetails) {
        User user = new User();
        user.setId(userDetails.getId());
        user.setUsername(userDetails.getUsername());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setUserRole(userDetails.getUserRole());
        user.setDateBirth(userDetails.getDateBirth());
        user.setSessionList(userDetails.getSessionList());
        user.setSocialLink(userDetails.getSocialLink());
        userRepository.save(user);
        return user;
    }

}
