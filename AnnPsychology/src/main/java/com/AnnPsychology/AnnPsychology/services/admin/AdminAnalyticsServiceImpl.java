package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Сервис создания аналитики
 */
@Service
@RequiredArgsConstructor
@Data
public class AdminAnalyticsServiceImpl implements iAdminAnalyticsService {

    /**
     * Адаптер для взаимодействия с Базой Данных
     */
    @Autowired
    private final AdapterRepository adapterRepository;

    /**
     * Заработано за год
     *
     * @return int результат
     */
    @Override
    public int earnedMoneyForTheYear() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE && i.getSessionDate().isAfter(LocalDateTime.now().minusYears(1)))
                .mapToInt(i -> i.getSessionPrice().intValue()).sum();
    }

    /**
     * Заработано за месяц
     *
     * @return int результат
     */
    @Override
    public int earnedMoneyForTheMonth() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE && i.getSessionDate().isAfter(LocalDateTime.now().minusMonths(1)))
                .mapToInt(i -> i.getSessionPrice().intValue()).sum();
    }

    /**
     * Заработано за неделю
     *
     * @return int результат
     */
    @Override
    public int earnedMoneyForTheWeek() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE && i.getSessionDate().isAfter(LocalDateTime.now().minusWeeks(1)))
                .mapToInt(i -> i.getSessionPrice().intValue()).sum();
    }

    /**
     * Количество сессий в год
     *
     * @return int результат
     */
    @Override
    public int countSessionsForTheYear() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE && i.getSessionDate().isAfter(LocalDateTime.now().minusYears(1)))
                .toList().size();
    }

    /**
     * Количество сессий за месяц
     *
     * @return int результат
     */
    @Override
    public int countSessionsForTheMonth() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE && i.getSessionDate().isAfter(LocalDateTime.now().minusMonths(1)))
                .toList().size();
    }

    /**
     * Количество сессий за неделю
     *
     * @return int результат
     */
    @Override
    public int countSessionsForTheWeek() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE && i.getSessionDate().isAfter(LocalDateTime.now().minusWeeks(1)))
                .toList().size();
    }

    /**
     * Количество всех выполненных сессий за все время
     *
     * @return int результат
     */
    @Override
    public int countAllDoneSessions() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(i -> i.getSessionStatus() == SessionStatus.SESSION_DONE)
                .toList().size();
    }

}
