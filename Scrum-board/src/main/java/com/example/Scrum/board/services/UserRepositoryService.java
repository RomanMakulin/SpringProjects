package com.example.Scrum.board.services;

import com.example.Scrum.board.models.User;
import com.example.Scrum.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRepositoryService {
    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(int id){
        return userRepository.getById(id);
    }

    public void update(User user){
        userRepository.updateUser(user.getFirstName(), user.getEmail(), user.getId());
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
