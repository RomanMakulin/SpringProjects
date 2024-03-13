package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface iAdminSessionService {
    AdapterRepository getAdapterRepository();
    Session getSessionById(Long sessionId, AdapterRepository adapterRepository);
    User getUserBySessionId(Long sessionId, AdapterRepository adapterRepository);
    List<Session> getAllSessions();
    boolean cancelSession(Long id);
    List<Session> getLatestWithoutHW();
    void giveSessionLink(Long id, String link);
    void giveSessionHomeWork(Long id, String sessionHomework);
    void editSessionDateByAdmin(Long sessionId, LocalDate date, LocalTime time);
    void setDone(List<Session> sessionList);
    List<Session> getLatest();
}
