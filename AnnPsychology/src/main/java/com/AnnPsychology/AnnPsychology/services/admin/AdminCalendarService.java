package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class AdminCalendarService implements iAdminCalendarService {
    @Autowired
    private final AdapterRepository adapterRepository;

    @Override
    public List<SessionDate> getCalendarDatesList() {
        return adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(SessionDate::getSessionDate)).toList();
    }

    @Override
    public void newOpenSessionDate(LocalDateTime localDateTime) {
        SessionDate sessionDate = new SessionDate();
        sessionDate.setSessionDate(localDateTime);
        adapterRepository.getDateRepository().save(sessionDate);
    }

    @Override
    public void deleteOpenSessionDate(Long sessionDateID) {
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(sessionDateID).orElseThrow();
        adapterRepository.getDateRepository().delete(sessionDate);
    }
}
