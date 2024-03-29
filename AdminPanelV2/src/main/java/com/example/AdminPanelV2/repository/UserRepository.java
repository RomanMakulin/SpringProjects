package com.example.AdminPanelV2.repository;

import com.example.AdminPanelV2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Необходимый метод для Spring Security
     *
     * @param username имя пользователя
     * @return результат поиска пользователя
     */
    Optional<User> getByUsername(String username);
}
