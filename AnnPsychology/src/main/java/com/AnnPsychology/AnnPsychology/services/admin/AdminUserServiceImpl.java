package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис управления пользователями
 */
@Service
@RequiredArgsConstructor
@Data
public class AdminUserServiceImpl implements iAdminUserService {

    /**
     * Репозиторий для взаимодействия с Базой Данных
     */
    @Autowired
    private final UserRepository userRepository;

    /**
     * Список всех пользователей
     *
     * @return список всех пользователей
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Получить пользователя по ID
     *
     * @param id ID пользователя
     * @return пользователь
     */
    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * Изменить стоимость сессии у пользователя
     *
     * @param id       ID пользователя
     * @param newPrice новая цена за сессии
     */
    @Override
    public void changePriceUser(Long id, BigDecimal newPrice) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPrice(newPrice);
        userRepository.save(user);
    }

}
