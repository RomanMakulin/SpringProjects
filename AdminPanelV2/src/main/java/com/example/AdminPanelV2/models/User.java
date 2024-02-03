package com.example.AdminPanelV2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private int age;
    private LocalDateTime dateCreate = LocalDateTime.now();
    private String image;

    /**
     * Парсим роль для frontend
     *
     * @return строковое представление роли
     */
    public String getParseRole() {
        return getRole().name().split("_")[1];
    }

    /**
     * Парсим дату для frontend
     *
     * @return строковое представление даты
     */
    public String getParseDate() {
        return getDateCreate().format(DateTimeFormatter.ofPattern("dd.MM.uuuu, HH:mm"));
    }
}
