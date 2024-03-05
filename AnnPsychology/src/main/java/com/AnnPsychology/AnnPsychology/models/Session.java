package com.AnnPsychology.AnnPsychology.models;

import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "user_session")
@Data
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_price")
    private BigDecimal sessionPrice;
    @Column(name = "session_link")
    private String sessionLink;
    @Column(name = "payment_status")
    private boolean paymentStatus;
    @Column(name = "session_status")
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "date_id", referencedColumnName = "id")
    private SessionDate sessionDate;
    @Column(name = "session_homework")
    private String sessionHomework;

    public LocalDateTime getDate(){
        if (sessionDate == null) return LocalDateTime.now();
        return sessionDate.getSessionDate();
    }

    public Session(User user, LocalDate date, LocalTime time) {
        this.user = user;
        this.sessionPrice = user.getPrice();
        this.sessionDate = new SessionDate();
        sessionDate.setSessionDate(LocalDateTime.of(date, time));
        this.sessionStatus = SessionStatus.SESSION_ACTIVE;
    }

    // добавить конструктор, где принимается пользователь (одно поле)
    // Constructor(User user)
    // this.sessionPrice = user.getPrice();
    // this.paymentStatus = STATUS...;
    
}
