package com.AnnPsychology.AnnPsychology.services.AdminPageService;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {
    List<Session> getAllSessions();
    List<Session> getRecentlyCompletedSessions();
    List<User> getAllUsers();
    void changePrice(Long id, BigDecimal newPrice);
}
