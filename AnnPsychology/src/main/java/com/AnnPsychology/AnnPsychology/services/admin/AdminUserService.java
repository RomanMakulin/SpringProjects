package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class AdminUserService {
    private final UserRepository userRepository;

    /**
     * Список всех пользователей
     *
     * @return список всех пользователей
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public void changePriceUser(Long id, BigDecimal newPrice) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPrice(newPrice);
        userRepository.save(user);
    }

}
