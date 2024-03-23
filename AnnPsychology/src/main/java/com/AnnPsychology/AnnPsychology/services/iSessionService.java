package com.AnnPsychology.AnnPsychology.services;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import java.util.List;

public interface iSessionService {
    Session getSessionById(Long sessionId, AdapterRepository adapterRepository);
    User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository);
    List<Session> getAllSessionsAbstract(List<Session> sessionList, AdapterRepository adapterRepository);
    void cancelSession(Session session, AdapterRepository adapterRepository);
    void sortSessionList(List<Session> sessionList);
    void setDone(List<Session> sessionList, AdapterRepository adapterRepository);
}
