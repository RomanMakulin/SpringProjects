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
@Table(name = "date_2")
@Data
@NoArgsConstructor
public class SessionDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_date")
    private LocalDateTime sessionDate;
    @Column(name = "busy")
    private boolean open;

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
        return Arrays.asList(dayOfWeekName, parsingDate());

    }

    public String parsingDate() {
        return sessionDate.format(DateTimeFormatter.ofPattern("dd.MM, HH:mm"));
    }
}
