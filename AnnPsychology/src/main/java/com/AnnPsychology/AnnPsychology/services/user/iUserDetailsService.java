package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.User;

public interface iUserDetailsService {
    boolean createUser(User userDetails);
    void updateUser(Long id, User userDetails);
    User getAuthUser();
}
