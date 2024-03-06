package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.repository.DateRepository;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import com.AnnPsychology.AnnPsychology.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        if (!validCheck(date, time)) return false;
        User updUser = adapterRepository.getUserRepository().findById(id).orElseThrow();
        updUser.getSessionList().add(new Session(updUser, date, time));
        adapterRepository.getUserRepository().save(updUser);
        return true;
    }

    public String getHomeWork(Long sessionId) {
        return adapterRepository.getSessionsRepository().findById(sessionId).orElseThrow().getSessionHomework();
    }

    public boolean cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        LocalDateTime sessionTime = session.getSessionDate().getSessionDate();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysDiff = ChronoUnit.DAYS.between(currentTime, sessionTime);

        if (daysDiff >= daysForCancel) {
            // TO DO: возврат денег

            session.setSessionStatus(SessionStatus.SESSION_CANCELLED);
            SessionDate sessionDate = adapterRepository.getDateRepository().getBySessionDate(session.getSessionDate().getSessionDate());
            adapterRepository.getDateRepository().deleteById(sessionDate.getId());
            adapterRepository.getSessionsRepository().save(session);
            return true;
        } else return false;
    }

    public List<Session> sortSessions(List<Session> sessionList) {

        // Проверить результат работы сортировки
        
        return sessionList.stream()
    .sorted((s1, s2) -> {
        int result = s1.getSessionStatus().compareTo(s2.getSessionStatus());
        if (result == 0) {
            result = s1.getSessionDate().getSessionDate().compareTo(s2.getSessionDate().getSessionDate());
        }
        return result;
    })
    .toList();

        // return sessionList.stream()
        //         .sorted(Comparator.comparing(Session::getSessionStatus, (status1, status2) -> {
        //             if (status1 == SessionStatus.SESSION_ACTIVE ||
        //                     status1 == SessionStatus.SESSION_DONE ||
        //                     status1 == SessionStatus.SESSION_CANCELLED) {
        //                 return -1;
        //             } else if (status2 == SessionStatus.SESSION_ACTIVE ||
        //                     status2 == SessionStatus.SESSION_DONE ||
        //                     status2 == SessionStatus.SESSION_CANCELLED) {
        //                 return 1;
        //             }
        //             return 0;
        //         }).thenComparing(Session::getDate)).toList();
    }


    //service methods
    public boolean validCheck(LocalDate date, LocalTime time) {
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        adapterRepository.getDateRepository().findAll().forEach(item -> {
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
