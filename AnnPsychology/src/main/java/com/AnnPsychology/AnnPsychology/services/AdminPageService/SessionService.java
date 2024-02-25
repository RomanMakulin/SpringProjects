package com.AnnPsychology.AnnPsychology.services.AdminPageService;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class SessionService {
    private final SessionsRepository sessionsRepository;

    public List<Session> getAll() {
        return sessionsRepository.findAll();
    }
}
