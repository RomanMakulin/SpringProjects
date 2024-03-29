package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface iUserSessionService {
    List<Session> getAllSessions();
    String getUserHomework(Long sessionID);
    AdapterRepository getAdapterRepository();
    Session getSessionById(Long sessionId, AdapterRepository adapterRepository);
    User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository);
    List<SessionDate> openSessionDateList();
    void createNewSession();
    String reserveSession(Long dateID) throws JsonProcessingException;
    boolean cancelSession(Long id);
}
