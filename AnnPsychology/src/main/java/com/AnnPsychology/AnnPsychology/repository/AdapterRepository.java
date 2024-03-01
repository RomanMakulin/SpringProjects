package com.AnnPsychology.AnnPsychology.repository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@RequiredArgsConstructor
@Data
public class AdapterRepository {
    private final DateRepository dateRepository;
    private final SessionsRepository sessionsRepository;
    private final UserRepository userRepository;
}
