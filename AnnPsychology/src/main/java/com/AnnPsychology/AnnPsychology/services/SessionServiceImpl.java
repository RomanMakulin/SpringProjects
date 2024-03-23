package com.AnnPsychology.AnnPsychology.services;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Общий сервис управления сессиями (админ + пользователь)
 */
public abstract class SessionServiceImpl implements iSessionService {

    /**
     * Получить сессию по ID
     *
     * @param sessionId         id
     * @param adapterRepository адаптер взаимодействия с базой данных
     * @return нужная сессия
     */
    @Override
    public Session getSessionById(Long sessionId, AdapterRepository adapterRepository) {
        Optional<Session> session = adapterRepository.getSessionsRepository().findById(sessionId);
        return session.orElseThrow();
    }

    @Override
    public List<Session> getAllSessionsAbstract(List<Session> sessionList, AdapterRepository adapterRepository) {
        sortSessionList(sessionList);
        setDone(sessionList, adapterRepository);
        return sessionList;
    }

    /**
     * Получить пользователя
     *
     * @param sessionId         id сессии
     * @param adapterRepository адаптер взаимодействия с базой данных
     * @return пользователь
     */
    @Override
    public User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository) {
        return adapterRepository.getSessionsRepository().findById(sessionId).orElseThrow().getUser();
    }

    /**
     * Отменить сессию
     *
     * @param session           сессия для отмены
     * @param adapterRepository адаптер взаимодействия с базой данных
     */
    @Override
    public void cancelSession(Session session, AdapterRepository adapterRepository) {
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);

        SessionDate sessionDate1 = adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().equals(session.getSessionDate())).findAny().get();

        sessionDate1.setOpen(true);
        adapterRepository.getDateRepository().save(sessionDate1);
    }

    /**
     * Сортировка списка сессий
     *
     * @param sessionList список сессий
     */
    @Override
    public void sortSessionList(List<Session> sessionList) {
        sessionList.sort(Comparator.comparing((Session s) -> {
                    if (s.getSessionStatus() == SessionStatus.SESSION_CANCELLED) return 1;
                    if (s.getSessionStatus() == SessionStatus.SESSION_ACTIVE) return -1;
                    return 0;
                })
                .thenComparing(Session::getDate));
    }

    /**
     * Получение списка сессий и обновления их статуса
     *
     * @param sessionList       передваемый список сессий
     * @param adapterRepository адаптер взаимодействия с базой данных
     */
    @Override
    public void setDone(List<Session> sessionList, AdapterRepository adapterRepository) {
        sessionList.forEach(item -> {
            if (item.getSessionStatus() == SessionStatus.SESSION_ACTIVE && item.getSessionDate().isBefore(LocalDateTime.now())) {
                item.setSessionStatus(SessionStatus.SESSION_DONE);
                adapterRepository.getSessionsRepository().save(item);
            }
        });
    }

}
