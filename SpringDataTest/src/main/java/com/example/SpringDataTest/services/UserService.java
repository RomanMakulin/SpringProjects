package com.example.SpringDataTest.services;

import com.example.SpringDataTest.models.User;
import com.example.SpringDataTest.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class UserService {
    private UserRepo userRepo;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public void createUser(User user){
        userRepo.save(user);
    }

    public void updateName(User user, String name){
        user.setFirstName(name);
    }

    public void deleteUser(User user){
        userRepo.delete(user);
    }

}
