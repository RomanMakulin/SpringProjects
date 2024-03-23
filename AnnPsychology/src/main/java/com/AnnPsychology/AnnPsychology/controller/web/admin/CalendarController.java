package com.AnnPsychology.AnnPsychology.controller.web.admin;

import com.AnnPsychology.AnnPsychology.services.admin.iAdminCalendarService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Контроллер администратора (календарь)
 */
@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class CalendarController {

    /**
     * Интерфейс логики по формирования календаря
     */
    private final iAdminCalendarService adminCalendarService;

    /**
     * Запрос на получение страницы календаря
     * @param model модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("closeDates", adminCalendarService.getCalendarDatesList());
        return "admin/calendar.html";
    }

    /**
     * Запрос на создание нового окна даты в календаре
     * @param date дата
     * @param time время
     * @return html календаря
     */
    @PostMapping("/calendar/new")
    public String openSession(@ModelAttribute("date") LocalDate date,
                              @ModelAttribute("time") LocalTime time) {
        adminCalendarService.newOpenSessionDate(LocalDateTime.of(date, time));
        return "redirect:/admin/calendar";
    }

    /**
     * Запрос на отмену/удаление окна дат
     * @param sessionDateID ID сессии
     * @return html календаря
     */
    @GetMapping("/calendar/cancel-date/{id}")
    public String openSession(@PathVariable("id") Long sessionDateID) {
        adminCalendarService.deleteOpenSessionDate(sessionDateID);
        return "redirect:/admin/calendar";
    }
}
