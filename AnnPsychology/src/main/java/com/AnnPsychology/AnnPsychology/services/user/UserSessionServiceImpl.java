package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.services.SessionServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<SessionDate> openSessionDateList() {
        return adapterRepository.getDateRepository().findAll().stream()
                .filter(SessionDate::isOpen).toList();
    }

    @Override
    public void signUpSession(Long dateID) {
        User user = customUserDetailsServiceImpl.getAuthUser();
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();

        Session session = new Session(user, sessionDate.getSessionDate());
        sessionDate.setOpen(false);

        adapterRepository.getDateRepository().save(sessionDate);
        adapterRepository.getSessionsRepository().save(session);

//        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();
//        sessionDate.setOpen(false);
//        adapterRepository.getSessionsRepository().save(new Session(user, sessionDate.getSessionDate()));
    }

//    @Override
//    public boolean signUpSession(Long id, LocalDate date, LocalTime time) {
//        if (!new ValidationSessionService().validCheck(date, time, adapterRepository)) return false;
//        User updUser = adapterRepository.getUserRepository().findById(id).orElseThrow();
//        updUser.getSessionList().add(new Session(updUser, date, time));
//        if (updUser.getSessionList().size() == 1) updUser.setPrice(new BigDecimal(2000));
//        adapterRepository.getUserRepository().save(updUser);
//        return true;
//    }

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
        long daysDiff = ChronoUnit.DAYS.between(LocalDateTime.now(), session.getSessionDate()); // day difference
        if (!(daysDiff >= daysForCancel)) return false;
        cancelAndDelete(session, adapterRepository);
        return true;
    }

    @Override
    public String getUserHomework(Long sessionID) {
        return getSessionById(sessionID, adapterRepository).getSessionHomework();
    }
}
