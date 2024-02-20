package com.AnnPsychology.AnnPsychology.models;

import com.AnnPsychology.AnnPsychology.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String username;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "social_link")
    private String socialLink;
    @Column(name = "date_birth")
    private LocalDateTime dateBirth;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Session> sessionList;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String password;
}
