package com.AnnPsychology.AnnPsychology.models;

import com.AnnPsychology.AnnPsychology.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
//@NoArgsConstructor
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
    @Column(name = "phone")
    private String phone;
    @Column(name = "date_birth")
    private LocalDate dateBirth;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Session> sessionList;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String password;
    private BigDecimal price;

    public int getAge(){
        return Period.between(dateBirth, LocalDate.now()).getYears();
    }

    public User(){
        this.sessionList = new ArrayList<>();
        this.userRole = UserRole.ROLE_USER;
        this.price = new BigDecimal(2000);
    }
    
    public String getStringPrice(){
        return this.price.intValue() + " руб.";
    }

    public int getSessionsCount(){
        return sessionList.size();
    }

}
