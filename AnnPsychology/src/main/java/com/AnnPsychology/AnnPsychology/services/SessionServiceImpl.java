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

public abstract class SessionServiceImpl implements iSessionService {
    @Override
    public Session getSessionById(Long sessionId, AdapterRepository adapterRepository) {
        Optional<Session> session = adapterRepository.getSessionsRepository().findById(sessionId);
        return session.orElseThrow();
    }

    @Override
    public User getUserBySessionId(Long id, AdapterRepository adapterRepository) {
        return adapterRepository.getSessionsRepository().findById(id).orElseThrow().getUser();
    }

    @Override
    public void cancelAndDelete(Session session, AdapterRepository adapterRepository) {
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);

        SessionDate sessionDate1 = adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().equals(session.getSessionDate())).findAny().get();

        sessionDate1.setOpen(true);
        adapterRepository.getDateRepository().save(sessionDate1);
    }

    @Override
    public void sortSessionList(List<Session> sessionList) {
        sessionList.sort(Comparator.comparing((Session s) -> {
                    if (s.getSessionStatus() == SessionStatus.SESSION_CANCELLED) return 1;
                    if (s.getSessionStatus() == SessionStatus.SESSION_ACTIVE) return -1;
                    return 0;
                })
                .thenComparing(Session::getDate));
    }

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
