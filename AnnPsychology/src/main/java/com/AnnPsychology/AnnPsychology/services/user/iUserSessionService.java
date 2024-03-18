package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface iUserSessionService {
    List<Session> getAllSessions();
    String getUserHomework(Long sessionID);
    AdapterRepository getAdapterRepository();
    Session getSessionById(Long sessionId, AdapterRepository adapterRepository);
    User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository);
    boolean signUpSession(Long id, LocalDate date, LocalTime time);
    // List<Session> getAllSessions();
    boolean cancelSession(Long id);
}
