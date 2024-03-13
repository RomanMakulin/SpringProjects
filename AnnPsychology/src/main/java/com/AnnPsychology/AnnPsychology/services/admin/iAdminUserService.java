package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface iAdminUserService {
    List<User> getAllUsers();
    User getUserByID(Long id);
    void changePriceUser(Long id, BigDecimal newPrice);
}
