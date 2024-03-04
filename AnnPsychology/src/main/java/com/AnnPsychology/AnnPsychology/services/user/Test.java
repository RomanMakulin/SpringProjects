package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Test implements Comparator<SessionStatus> {

    @Override
    public int compare(SessionStatus o1, SessionStatus o2) {
        return 0;
    }
}
