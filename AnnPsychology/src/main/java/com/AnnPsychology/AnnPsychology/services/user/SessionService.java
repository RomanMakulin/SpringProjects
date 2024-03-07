package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.repository.DateRepository;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import com.AnnPsychology.AnnPsychology.services.publicService.PublicSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class SessionService {
    @Autowired
    private PublicSessionService publicSessionService;
    private final AdapterRepository adapterRepository;
    private final long daysForCancel = 1;

    public Session getById(Long id) {
        Optional<Session> session = adapterRepository.getSessionsRepository().findById(id);
        return session.orElseThrow();
    }

    public User getUserBySessionId(Long id) {
        return adapterRepository.getSessionsRepository().findById(id).orElseThrow().getUser();
    }

    public boolean signUpSession(Long id, LocalDate date, LocalTime time) {
        if (!new ValidationSessionService().validCheck(date, time, adapterRepository)) return false;
        User updUser = adapterRepository.getUserRepository().findById(id).orElseThrow();
        updUser.getSessionList().add(new Session(updUser, date, time));
        if (updUser.getSessionList().size() == 1) updUser.setPrice(new BigDecimal(2000));
        adapterRepository.getUserRepository().save(updUser);
        return true;
    }

    public String getHomeWork(Long sessionId) {
        return adapterRepository.getSessionsRepository().findById(sessionId).orElseThrow().getSessionHomework();
    }

    public boolean cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        long daysDiff = ChronoUnit.DAYS.between(LocalDateTime.now(), session.getSessionDate().getSessionDate()); // day difference
        if (!(daysDiff >= daysForCancel)) return false;
        publicSessionService.cancelAndDeleteDate(session, adapterRepository);
        return true;
    }

    public List<Session> sortSessions(List<Session> sessionList) {
        return publicSessionService.sortSession(sessionList);
    }

}
