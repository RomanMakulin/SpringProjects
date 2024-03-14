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
public class AdminUserServiceImpl implements iAdminUserService {
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

    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void changePriceUser(Long id, BigDecimal newPrice) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPrice(newPrice);
        userRepository.save(user);
    }


    // TO DO: создать отдельный класс для вычисления аналитики
    //
    // Заработано за год (doneSessions.stream().filter(i -> i.statusDONE).mapToInt(i -> i.getSessionPrice).sum())
    // Заработано за месяц (doneSessions.stream().filter(i -> i.getDate.isAfter(LocalDateTime().now.minusMonth(1))).mapToInt(i -> i.getSessionPrice).sum())
    // Заработано за неделю (doneSessions.stream().filter(i -> i.getDate.isAfter(LocalDateTime().now.minusDays(7))).mapToInt(i -> i.getSessionPrice).sum())
    // Всего активных клиентов
    // Проведено сессий в этом месяце
    // Проведено сессий за этот год
    // Новых клиентов за месяц
    // Общее кол-во проведенных сессий

}
