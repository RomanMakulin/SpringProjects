package com.AnnPsychology.AnnPsychology.services;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.DateRepository;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Data
public class SessionService {
    private final SessionsRepository sessionsRepository;
    private final UserRepository userRepository;
    private final DateRepository dateRepository;
    private final long daysForCancel = 1;

    public Session getById(Long id) {
        Optional<Session> session = sessionsRepository.findById(id);
        return session.orElseThrow();
    }

    public User getUserBySessionId(Long id) {
        return sessionsRepository.findById(id).orElseThrow().getUser();
    }

    public boolean signUpSession(Long id, LocalDate date, LocalTime time) {
        if (validCheck(date, time)) {
            User updUser = userRepository.findById(id).orElseThrow();

            Session session = new Session();
            session.setUser(updUser);
            session.setSessionStatus(SessionStatus.SESSION_ACTIVE);
            session.setSessionPrice(updUser.getPrice());

            SessionDate sessionDate = new SessionDate();
            sessionDate.setSessionDate(LocalDateTime.of(date, time));
            session.setSessionDate(sessionDate);

            updUser.getSessionList().add(session);
            userRepository.save(updUser);
            return true;
        } else return false;
    }

    public boolean cancelSession(Long id) {
        Session session = sessionsRepository.findById(id).orElseThrow();
        LocalDateTime sessionTime = session.getSessionDate().getSessionDate();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysDiff = ChronoUnit.DAYS.between(currentTime, sessionTime);

        if (daysDiff >= daysForCancel) {
            // TO DO: возврат денег
            session.setSessionStatus(SessionStatus.SESSION_CANCELLED);
            sessionsRepository.save(session);
            return true;
        } else return false;
    }

    public List<Session> sortSessions(List<Session> sessionList) {
        return sessionList.stream()
                .sorted(Comparator.comparing(Session::getSessionStatus).thenComparing(Session::getDate)).toList();
    }

    //service
    public boolean validCheck(LocalDate date, LocalTime time) {
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        dateRepository.findAll().forEach(item -> {
            localDateTimeList.add(item.getSessionDate());
        });

        boolean validDateContain = localDateTimeList.contains(LocalDateTime.of(date, time));

        boolean validSessionBefore = localDateTimeList.contains(LocalDateTime.of(date, time.plusHours(1))) |
                localDateTimeList.contains(LocalDateTime.of(date, time.plusMinutes(30)));

        boolean validSessionAfter = localDateTimeList.contains(LocalDateTime.of(date, time.minusHours(1))) |
                localDateTimeList.contains(LocalDateTime.of(date, time.minusMinutes(30)));

        boolean validSessionToLate = LocalDateTime.of(date, time).isAfter(LocalDateTime.now());

        return !validDateContain & !validSessionBefore & !validSessionAfter & validSessionToLate;
    }

}
