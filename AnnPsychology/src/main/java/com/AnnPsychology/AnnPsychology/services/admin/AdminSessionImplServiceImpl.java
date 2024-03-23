package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.services.SessionServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

/**
 * Сервис управления сессиями
 */
@EqualsAndHashCode(callSuper = true)
@Service
@RequiredArgsConstructor
@Data
public class AdminSessionImplServiceImpl extends SessionServiceImpl implements iAdminSessionService {

    /**
     * Адаптер для взаимодействия с Базой Данных
     */
    @Autowired
    private final AdapterRepository adapterRepository;

    /**
     * Получить список всех сессий
     * В реализации администратора
     *
     * @return список сессий
     */
    @Override
    public List<Session> getAllSessions() {
        return getAllSessionsAbstract(adapterRepository.getSessionsRepository().findAll(), adapterRepository);
    }

    /**
     * Отменить сессию
     *
     * @param id ID сессии
     * @return логический результат завершения метода
     */
    @Override
    public boolean cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        cancelSession(session, adapterRepository);
        return true;
    }

    /**
     * Получить список недавно завершенных сессий
     * 1. Фильтр по статусу сессий (done)
     * 2. Сортировка по разнице даты и времени сессии с текущим now (сессии за неделю)
     *
     * @return список сессий
     */
    @Override
    public List<Session> getLatest() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(item -> item.getSessionStatus() == SessionStatus.SESSION_DONE)
                .sorted(Comparator.comparing(s -> s.getSessionDate()))
                .toList();
    }

    /**
     * Получить список сессий без домашних работ
     *
     * @return список сессий
     */
    @Override
    public List<Session> getLatestWithoutHW() {
        return getLatest().stream()
                .filter(item -> item.getSessionHomework() == null)
                .toList();
    }

    /**
     * Выдать ссылку на сессию (видео конференция)
     *
     * @param id   ID сессии
     * @param link ссылка
     */
    @Override
    public void giveSessionLink(Long id, String link) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        session.setSessionLink(link);
        adapterRepository.getSessionsRepository().save(session);
    }

    /**
     * Прикрепить домашнее задание к сессии
     *
     * @param id              ID сессии
     * @param sessionHomework домашнее задание
     */
    @Override
    public void giveSessionHomeWork(Long id, String sessionHomework) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        session.setSessionHomework(sessionHomework);
        adapterRepository.getSessionsRepository().save(session);
    }

    /**
     * Изменить время сессии
     *
     * @param sessionId ID сессии
     * @param date      дата
     * @param time      время
     */
    @Override
    public void editSessionDateByAdmin(Long sessionId, LocalDate date, LocalTime time) {
        Session session = adapterRepository.getSessionsRepository().findById(sessionId).orElseThrow();
        session.setSessionDate(LocalDateTime.of(date, time));
        adapterRepository.getSessionsRepository().save(session);
    }

}
