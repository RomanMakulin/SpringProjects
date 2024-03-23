package com.AnnPsychology.AnnPsychology.services.admin;

import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * Сервис логики календаря администратора
 */
@Service
@RequiredArgsConstructor
@Data
public class AdminCalendarService implements iAdminCalendarService {

    /**
     * Адаптер для взаимодействия с Базой Данных
     */
    @Autowired
    private final AdapterRepository adapterRepository;

    /**
     * В отсортированном виде получить список всех выставленных окон дл записи
     *
     * @return список дат
     */
    @Override
    public List<SessionDate> getCalendarDatesList() {
        return adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(SessionDate::getSessionDate)).toList();
    }

    /**
     * Задать новое окно для записи
     *
     * @param localDateTime дата нового окна под запись
     */
    @Override
    public void newOpenSessionDate(LocalDateTime localDateTime) {
        SessionDate sessionDate = new SessionDate();
        sessionDate.setSessionDate(localDateTime);
        adapterRepository.getDateRepository().save(sessionDate);
    }

    /**
     * Удалить свободное окно под запись
     *
     * @param sessionDateID ID даты
     */
    @Override
    public void deleteOpenSessionDate(Long sessionDateID) {
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(sessionDateID).orElseThrow();
        adapterRepository.getDateRepository().delete(sessionDate);
    }
}
