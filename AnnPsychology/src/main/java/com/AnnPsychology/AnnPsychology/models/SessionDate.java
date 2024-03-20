package com.AnnPsychology.AnnPsychology.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

//@Entity
//@Table(name = "sessions_date")
//@Data
//@NoArgsConstructor
//public class SessionDate {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "session_date")
//    private LocalDateTime sessionDate;
//
//    public String parsingDate(){
//        return sessionDate.format(DateTimeFormatter.ofPattern("dd.MM, HH:mm"));
//    }
//}

@Entity
@Table(name = "open_dates")
@Data
@NoArgsConstructor
public class SessionDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_date")
    private LocalDateTime sessionDate;
    @Column(name = "is_open")
    private boolean open = true;

    public List<String> parsingWithDayName() {
        String dayOfWeekName = switch (sessionDate.getDayOfWeek()) {
            case MONDAY -> "Понедельник";
            case TUESDAY -> "Вторник";
            case WEDNESDAY -> "Среда";
            case THURSDAY -> "Четверг";
            case FRIDAY -> "Пятница";
            case SATURDAY -> "Суббота";
            case SUNDAY -> "Воскресенье";
        };
        String isFree = "не занята";
        if (!open) isFree = "занята";
        return Arrays.asList(dayOfWeekName, parsingDate(), isFree);
    }

    public String parsingDate() {
        return sessionDate.format(DateTimeFormatter.ofPattern("dd.MM, HH:mm"));
    }
}
