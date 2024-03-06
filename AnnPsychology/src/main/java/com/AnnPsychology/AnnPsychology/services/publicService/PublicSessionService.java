package com.AnnPsychology.AnnPsychology.services.publicService;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Data
public class PublicSessionService {

    /**
     * Custom sorting session list
     *
     * @param sessionList user's session list
     * @return sorted user's session list
     */
    public List<Session> sortSession(List<Session> sessionList) {
        sessionList.sort(Comparator.comparing((Session s) -> {
                    if (s.getSessionStatus() == SessionStatus.SESSION_CANCELLED) return 1;
                    if (s.getSessionStatus() == SessionStatus.SESSION_ACTIVE) return -1;
                    return 0;
                })
                .thenComparing(Session::getDate));
        return sessionList;
    }

    /**
     * Cancel and delete session's date from DB
     *
     * @param session           current session for delete
     * @param adapterRepository adapter db
     */
    public void cancelAndDeleteDate(Session session, AdapterRepository adapterRepository) {
        // TO DO: возврат денежных средств
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);
        SessionDate sessionDate = adapterRepository.getDateRepository().getBySessionDate(session.getSessionDate().getSessionDate());
        adapterRepository.getDateRepository().deleteById(sessionDate.getId());
        adapterRepository.getSessionsRepository().save(session);
    }

}
