package com.example.AdminPanel.repository;

import com.example.AdminPanel.model.Role;
import com.example.AdminPanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getByRole(Role role);
    User getByUsername(String username);
}
