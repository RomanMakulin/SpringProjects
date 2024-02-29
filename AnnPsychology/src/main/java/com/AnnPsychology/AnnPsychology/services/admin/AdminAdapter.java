package com.AnnPsychology.AnnPsychology.services.admin;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class AdminAdapter {
    private final AdminUserService adminUserService;
    private final AdminSessionService adminSessionService;
}
