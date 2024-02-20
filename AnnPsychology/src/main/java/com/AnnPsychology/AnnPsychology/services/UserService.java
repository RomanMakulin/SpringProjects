package com.AnnPsychology.AnnPsychology.services;

import com.AnnPsychology.AnnPsychology.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User createUser(User userDetails);

}
