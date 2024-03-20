package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface iUserSessionService {
    List<Session> getAllSessions();
    String getUserHomework(Long sessionID);
    AdapterRepository getAdapterRepository();
    Session getSessionById(Long sessionId, AdapterRepository adapterRepository);
    User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository);
    List<SessionDate> openSessionDateList();
    void signUpSession(Long dateID);;
    boolean cancelSession(Long id);
}
