package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationSessionService {

    /**
     * Validation check
     *
     * @param date              new date session
     * @param time              new time session
     * @param adapterRepository adapter
     * @return true or false to create new session
     */
    public boolean validCheck(LocalDate date, LocalTime time, AdapterRepository adapterRepository) {
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        adapterRepository.getDateRepository().findAll().forEach(item -> {
            localDateTimeList.add(item.getSessionDate());
        });

        boolean validDateContain = localDateTimeList.contains(LocalDateTime.of(date, time));

        boolean validSessionBefore = localDateTimeList.contains(LocalDateTime.of(date, time.plusHours(1))) |
                localDateTimeList.contains(LocalDateTime.of(date, time.plusMinutes(30)));

        boolean validSessionAfter = localDateTimeList.contains(LocalDateTime.of(date, time.minusHours(1))) |
                localDateTimeList.contains(LocalDateTime.of(date, time.minusMinutes(30)));

        boolean validSessionToLate = LocalDateTime.of(date, time).isAfter(LocalDateTime.now());

        return !validDateContain & !validSessionBefore & !validSessionAfter & validSessionToLate;
    }
}
