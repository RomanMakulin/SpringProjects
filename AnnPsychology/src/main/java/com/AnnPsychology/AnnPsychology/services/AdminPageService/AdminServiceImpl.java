package com.AnnPsychology.AnnPsychology.services.AdminPageService;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class AdminServiceImpl implements AdminService {

    private final SessionsRepository sessionsRepository;
    private final UserRepository userRepository;

    /**
     * Получить список всех сессий в отсортированном виде
     *
     * @return Отсортированный спиоск
     */
    @Override
    public List<Session> getAllSessions() {
        return sessionsRepository.findAll().stream().sorted(
                Comparator.comparing(s -> s.getSessionDate().getSessionDate())
        ).collect(Collectors.toList());
    }

    /**
     * Получить недавно завершенные сессии за 7 дней
     *
     * @return Отсортированный и отфильтрованный список
     */
    @Override
    public List<Session> getRecentlyCompletedSessions() {
        return sessionsRepository.findAll().stream().
                filter(item -> item.getSessionDate().getSessionDate().isAfter(LocalDateTime.now().minusDays(7))).
                filter(item -> item.getSessionStatus().equals(SessionStatus.SESSION_DONE)).
                sorted(Comparator.comparing(s -> s.getSessionDate().getSessionDate())).
                toList();
    }

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
    public void changePrice(Long id, BigDecimal newPrice) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPrice(newPrice);
        userRepository.save(user);
    }
}
