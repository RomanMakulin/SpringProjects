package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import com.AnnPsychology.AnnPsychology.services.publicService.PublicSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class AdminSessionService {
    @Autowired
    private PublicSessionService publicSessionService;
    private final AdapterRepository adapterRepository;

    /**
     * Получить список всех сессий в отсортированном виде
     *
     * @return Отсортированный спиоск
     */
    public List<Session> getAllSessions() {

        List<Session> sessionList = adapterRepository.getSessionsRepository()
                .findAll();

        sessionList = publicSessionService.sortSession(sessionList);
        setDone(sessionList);
        return sessionList;
    }

    /**
     * Получить список недавно завершенных сессий
     * 1. Фильтр по статусу сессий (done)
     * <p>
     * 2. Фильтр по разнице даты и времени сессии с текущим now (сессии за неделю)
     *
     * @return список сессий
     */
    public List<Session> getLatest() {
        return adapterRepository.getSessionsRepository().findAll().stream()
                .filter(item -> item.getSessionStatus() == SessionStatus.SESSION_DONE)
                .sorted(Comparator.comparing(s -> s.getSessionDate().getSessionDate()))
                .toList();
    }

    public List<Session> getLatestWithoutHW() {
        return getLatest().stream()
                .filter(item -> item.getSessionHomework() == null)
                .toList();
    }

    public void giveSessionLink(Long id, String link) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        session.setSessionLink(link);
        adapterRepository.getSessionsRepository().save(session);
    }

    public void giveSessionHomeWork(Long id, String sessionHomework) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        session.setSessionHomework(sessionHomework);
        adapterRepository.getSessionsRepository().save(session);
    }

    public void editSessionDateByAdmin(Long sessionId, LocalDate date, LocalTime time) {
        Session session = adapterRepository.getSessionsRepository().findById(sessionId).orElseThrow();
        session.getSessionDate().setSessionDate(LocalDateTime.of(date, time));
        adapterRepository.getSessionsRepository().save(session);
    }

    public void cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        new PublicSessionService().cancelAndDeleteDate(session, adapterRepository);
    }

    public void setDone(List<Session> sessionList) {
        sessionList.forEach(item -> {
            if (item.getSessionStatus() == SessionStatus.SESSION_ACTIVE && item.getSessionDate().getSessionDate().isBefore(LocalDateTime.now())) {
                item.setSessionStatus(SessionStatus.SESSION_DONE);
                adapterRepository.getSessionsRepository().save(item);
            }
        });
    }

}
