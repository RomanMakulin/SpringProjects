package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.services.SessionServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


/**
 * Сервис для управления сессиями пользователя
 */
@EqualsAndHashCode(callSuper = true)
@Service
@Data
public class UserSessionServiceImpl extends SessionServiceImpl implements iUserSessionService {

    /**
     * Адаптер для взаимодействия с базой данных
     */
    @Autowired
    private final AdapterRepository adapterRepository;

    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    /**
     * Ограничитель дней для отмены сессий
     */
    private final long daysForCancel = 1; // поправить на часы (24 часа)

    /**
     * Удаление всех дат (прошелших сессий) из базы данных
     */
    public void deleteLastSessionDate() {
        adapterRepository.getDateRepository().findAll().stream().toList().forEach(i -> {
            if (i.getSessionDate().isBefore(LocalDateTime.now()))
                adapterRepository.getDateRepository().delete(i);
        });
    }

    /**
     * Получить список всех свободных окон (дат) для записи
     *
     * @return список дат
     */
    @Override
    public List<SessionDate> openSessionDateList() {
        deleteLastSessionDate();
        return adapterRepository.getDateRepository().findAll().stream()
                .filter(SessionDate::isOpen)
                .sorted(Comparator.comparing(SessionDate::getSessionDate)).toList();
    }

    /**
     * Создание новой сессии
     *
     * @param dateID нужная дата для регистрации сессии
     */
    @Override
    public void createNewSession(Long dateID) {
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();
        // User user = adapterRepository.getUserRepository().findById(customUserDetailsServiceImpl.getAuthUser().getId()).orElseThrow();
        User user = customUserDetailsServiceImpl.getAuthUser();
        Session session = new Session(user, sessionDate.getSessionDate());

        sessionDate.setOpen(false);
        adapterRepository.getDateRepository().save(sessionDate);
        adapterRepository.getSessionsRepository().save(session);
    }

    public void reserveSession(Long dateID) {
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();
        User user = customUserDetailsServiceImpl.getAuthUser();
        Session session = new Session(user, sessionDate.getSessionDate());
        sessionDate.setOpen(false);

        // Добавить новую сущность и записать в нее сохраненные данные заказа
        // adapterRepository.getDateRepository().save(sessionDate); 
        // adapterRepository.getSessionsRepository().save(session);
    }

    /**
     * Получение всех сессий пользователя в отсортированном порядке
     * В реализации пользователя
     *
     * @return Список сессий
     */
    @Override
    public List<Session> getAllSessions() {
        return getAllSessionsAbstract(customUserDetailsServiceImpl.getAuthUser().getSessionList(), adapterRepository);
    }

    /**
     * Отменить сессию
     *
     * @param id ID сессии
     * @return логический результат выполнения метода
     */
    @Override
    public boolean cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        long daysDiff = ChronoUnit.DAYS.between(LocalDateTime.now(), session.getSessionDate()); // дни поменять на часы

        if (!(daysDiff >= daysForCancel)) return false;
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);

        SessionDate sessionDate1 = adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().equals(session.getSessionDate())).findAny().get();
        sessionDate1.setOpen(true);

        adapterRepository.getDateRepository().save(sessionDate1);
        return true;
    }

    /**
     * Получить домашнюю работу по конкретной сессии
     *
     * @param sessionID ID сессии
     * @return домашняя работа в строковом представлении
     */
    @Override
    public String getUserHomework(Long sessionID) {
        return getSessionById(sessionID, adapterRepository).getSessionHomework();
    }
}
