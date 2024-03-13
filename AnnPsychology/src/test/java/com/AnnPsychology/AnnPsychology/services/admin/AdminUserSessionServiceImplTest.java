package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminUserSessionServiceImplTest {

    @Mock
    public SessionsRepository sessionsRepository;
    @Mock
    public AdminUserServiceImpl adminUserServiceImpl;
    @Mock
    public AdapterRepository adapterRepository;
    @InjectMocks
    public AdminSessionImplServiceImpl adminSessionService;


    @Test
    void getAllSessionsTest() {
        Mockito.when(sessionsRepository.findAll()).thenReturn(List.of(new Session()));
        assertEquals(sessionsRepository.findAll().size(), List.of(new Session()).size());
    }

    @Test
    void getLatestTest() {
        LocalDateTime dateForTest = LocalDateTime.of(2020, 10, 10, 10, 10);

        Session session1 = new Session();
        session1.setSessionDate(new SessionDate());
        session1.getSessionDate().setSessionDate(dateForTest);

        Session session2 = new Session();
        session2.setSessionDate(new SessionDate());
        session2.getSessionDate().setSessionDate(dateForTest.minusDays(1));

        Session session3 = new Session();
        session3.setSessionDate(new SessionDate());
        session3.getSessionDate().setSessionDate(dateForTest.plusMonths(1));

        Mockito.when(sessionsRepository.findAll()).thenReturn(List.of(session2, session1, session3));
        List<Session> sessionListForTest = sessionsRepository.findAll();
        sessionListForTest.stream().filter(item -> item.getSessionStatus() == SessionStatus.SESSION_DONE)
                .sorted(Comparator.comparing(s -> s.getSessionDate().getSessionDate()))
                .toList();

        List<Session> sortedList = Arrays.asList(session2, session1, session3);

        assertEquals(sortedList, sessionListForTest);
    }

    @Test
    void giveSessionLinkTest() {
        Session session = new Session();
        session.setSessionLink("link");
        assertEquals(session.getSessionLink(), "link");
    }

    @Test
    void giveSessionHomeWorkTest() {
        Session session = new Session();
        session.setSessionHomework("hw");
        assertEquals(session.getSessionHomework(), "hw");
    }

    @Test
    void editSessionDateByAdminTest() {
        Mockito.when(sessionsRepository.findById(1L)).thenReturn(Optional.of(new Session()));
        Session session = sessionsRepository.findById(1L).orElseThrow();
        LocalDateTime dateForTest = LocalDateTime.of(2020, 10, 10, 10, 10);

        session.setSessionDate(new SessionDate());
        session.getSessionDate().setSessionDate(dateForTest);
        assertEquals(dateForTest, session.getSessionDate().getSessionDate());
    }

    @Test
    void cancelSessionTest() {
        Mockito.when(sessionsRepository.findById(1L)).thenReturn(Optional.of(new Session()));
        Session session = sessionsRepository.findById(1L).orElseThrow();
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);
        assertEquals(SessionStatus.SESSION_CANCELLED, session.getSessionStatus());
    }

    @Test
    void setDoneTest(){
        LocalDateTime dateForTest = LocalDateTime.of(2020, 10, 10, 10, 10);

        Session session1 = new Session();
        session1.setSessionStatus(SessionStatus.SESSION_ACTIVE);
        session1.setSessionDate(new SessionDate());
        session1.getSessionDate().setSessionDate(dateForTest);

        Session session2 = new Session();
        session2.setSessionStatus(SessionStatus.SESSION_ACTIVE);
        session2.setSessionDate(new SessionDate());
        session2.getSessionDate().setSessionDate(dateForTest.minusDays(1));

        Session session3 = new Session();
        session3.setSessionStatus(SessionStatus.SESSION_ACTIVE);
        session3.setSessionDate(new SessionDate());
        session3.getSessionDate().setSessionDate(dateForTest.plusYears(10));

        List<Session> sessionList = Arrays.asList(session2, session1, session3);

        AtomicInteger countDone = new AtomicInteger();
        sessionList.forEach(item -> {
            if (item.getSessionStatus() == SessionStatus.SESSION_ACTIVE && item.getSessionDate().getSessionDate().isBefore(LocalDateTime.now())) {
                item.setSessionStatus(SessionStatus.SESSION_DONE);
                countDone.addAndGet(1);
            }
        });

        assertEquals(2, countDone.get());
    }

}