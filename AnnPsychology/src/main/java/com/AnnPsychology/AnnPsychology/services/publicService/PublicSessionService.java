package com.AnnPsychology.AnnPsychology.services.publicService;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;

import java.util.Comparator;
import java.util.List;

public class PublicSessionService {

    /**
     * Custom sorting session list
     *
     * @param sessionList user's session list
     * @return sorted user's session list
     */
    public List<Session> sortSession(List<Session> sessionList) {
        return sessionList.stream()
                .sorted(Comparator.comparing(Session::getSessionStatus, (status1, status2) -> {
                    if (status1 == SessionStatus.SESSION_ACTIVE ||
                            status1 == SessionStatus.SESSION_DONE ||
                            status1 == SessionStatus.SESSION_CANCELLED) {
                        return -1;
                    } else if (status2 == SessionStatus.SESSION_ACTIVE ||
                            status2 == SessionStatus.SESSION_DONE ||
                            status2 == SessionStatus.SESSION_CANCELLED) {
                        return 1;
                    }
                    return 0;
                }).thenComparing(Session::getDate)).toList();
    }
}
