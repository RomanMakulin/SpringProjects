package com.AnnPsychology.AnnPsychology.models;

import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    @Column(name = "session_date")
    private LocalDateTime sessionDate;
    @Column(name = "session_homework")
    private String sessionHomework;

    public LocalDateTime getDate() {
        if (sessionDate == null) return LocalDateTime.now();
        return sessionDate;
    }

    public Session(User user, LocalDateTime needDate) {
        this.user = user;
        this.sessionPrice = user.checkPrice();
        this.sessionDate = needDate;
        this.sessionStatus = SessionStatus.SESSION_ACTIVE;
    }

//    public BigDecimal checkPrice() {
//        if (user.getSessionList().isEmpty()) return new BigDecimal(2000);
//        return user.getPrice();
//    }

    public String parsingDate() {
        return sessionDate.format(DateTimeFormatter.ofPattern("dd.MM, HH:mm"));
    }

}
