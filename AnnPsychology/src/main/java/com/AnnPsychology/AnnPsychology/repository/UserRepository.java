package com.AnnPsychology.AnnPsychology.repository;

import com.AnnPsychology.AnnPsychology.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Необходимый метод для Spring Security
     *
     * @param username имя пользователя
     * @return результат поиска пользователя
     */
    Optional<User> getByUsername(String username);
}
