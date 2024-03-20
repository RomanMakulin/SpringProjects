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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@EqualsAndHashCode(callSuper = true)
@Service

@Data
public class UserSessionServiceImpl extends SessionServiceImpl implements iUserSessionService {
    @Autowired
    private final AdapterRepository adapterRepository;
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    private final long daysForCancel = 1;

    @Override
    public void deleteLastSessionDate() {
        adapterRepository.getDateRepository().findAll().stream().toList().forEach(i -> {
            if (i.getSessionDate().isBefore(LocalDateTime.now()))
                adapterRepository.getDateRepository().delete(i);
        });
    }

    @Override
    public List<SessionDate> openSessionDateList() {
        deleteLastSessionDate();
        return adapterRepository.getDateRepository().findAll().stream()
                .filter(SessionDate::isOpen)
                .sorted(Comparator.comparing(SessionDate::getSessionDate)).toList();
    }

    @Override
    public void signUpSession(Long dateID) {
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();
        User user = adapterRepository.getUserRepository().findById(customUserDetailsServiceImpl.getAuthUser().getId()).orElseThrow();
        Session session = new Session(user, sessionDate.getSessionDate());

        sessionDate.setOpen(false);
        adapterRepository.getDateRepository().save(sessionDate);
        adapterRepository.getSessionsRepository().save(session);
    }

    @Override
    public List<Session> getAllSessions() {
        List<Session> userSessionList = customUserDetailsServiceImpl.getAuthUser().getSessionList();
        sortSessionList(userSessionList);
        setDone(userSessionList, adapterRepository);
        return userSessionList;
    }

    @Override
    public boolean cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        long daysDiff = ChronoUnit.DAYS.between(LocalDateTime.now(), session.getSessionDate());

        if (!(daysDiff >= daysForCancel)) return false;
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);

        SessionDate sessionDate1 = adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().equals(session.getSessionDate())).findAny().get();
        sessionDate1.setOpen(true);

        adapterRepository.getDateRepository().save(sessionDate1);
        return true;
    }

    @Override
    public String getUserHomework(Long sessionID) {
        return getSessionById(sessionID, adapterRepository).getSessionHomework();
    }
}
