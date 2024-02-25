package com.AnnPsychology.AnnPsychology.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "sessions_date")
@Data
@NoArgsConstructor
public class SessionDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    public String parsingDate(){
        return sessionDate.format(DateTimeFormatter.ofPattern("dd.MM, HH:mm"));
    }
}
