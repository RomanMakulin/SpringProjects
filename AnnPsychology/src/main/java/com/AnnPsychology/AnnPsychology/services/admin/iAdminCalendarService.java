package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.SessionDate;

import java.time.LocalDateTime;
import java.util.List;

public interface iAdminCalendarService {
    List<SessionDate> getCalendarDatesList();
    void newOpenSessionDate(LocalDateTime localDateTime);
    void deleteOpenSessionDate(Long sessionDateID);
}
