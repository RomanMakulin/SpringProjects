package com.AnnPsychology.AnnPsychology.services;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;

import java.util.List;

public interface iSessionService {
    Session getSessionById(Long sessionId, AdapterRepository adapterRepository);
    User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository);
    List<Session> getAllSessions(List<Session> sessionList);
    boolean cancelSession(Long sessionId);
    void cancelAndDelete(Session session, AdapterRepository adapterRepository);
    void sortSessionList(List<Session> sessionList);

}
