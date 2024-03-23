package com.AnnPsychology.AnnPsychology.controller.web.user;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.services.user.iUserDetailsService;
import com.AnnPsychology.AnnPsychology.services.user.iUserSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер сессий пользователя
 */
@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserSessionController {

    /**
     * Интерфейс управления пользователем
     */
    private final iUserDetailsService userDetailsService;

    /**
     * Интерфейс управления сессиями
     */
    private final iUserSessionService userSessionService;

    /**
     * Запрос на получение страницы со всем списком сессий
     *
     * @param model модель взаимодействия с frontend
     * @return html
     */
    @GetMapping
    public String allSessions(Model model) {
        model.addAttribute("userSessions", userSessionService.getAllSessions());
        model.addAttribute("user", userDetailsService.getAuthUser());
        return "user/user.html";
    }

    /**
     * Форма создания новой сессии
     *
     * @param model модель взаимодействия с frontend
     * @return html
     */
    @GetMapping("/sign-up-session-form")
    public String signUpSessionForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("openDateList", userSessionService.openSessionDateList());
        return "user/go-session.html";
    }

    /**
     * Запрос создания новой сессии
     *
     * @param dateID ID даты для создания новой сессии
     * @return html
     */
    @PostMapping("/sign-up-session/{id}")
    public String signUpSession(@PathVariable("id") Long dateID) {
        userSessionService.createNewSession(dateID);
        return "redirect:/user";
    }

    /**
     * Отменить сессию
     *
     * @param id    ID сессии
     * @param model модель взаимодействия с frontend
     * @return true: редирект на страницу ЛК, false: html с ошибкой
     */
    @GetMapping("/cancel/{id}")
    public String cancelSession(@PathVariable("id") Long id, Model model) {
        if (userSessionService.cancelSession(id)) return "redirect:/user";
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("errorMessage", "Отмена сессии менее чем за сутки невозможна.");
        return "user/cancel-error.html";
    }

    /**
     * Получить домашнюю работу по конкретной сессии
     *
     * @param sessionId ID сессии
     * @param model     модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping("/hw/{id}")
    public String getHomeWork(@PathVariable("id") Long sessionId, Model model) {
        Session session = userSessionService.getSessionById(sessionId, userSessionService.getAdapterRepository());
        model.addAttribute("user", session.getUser());
        model.addAttribute("hw", session.getSessionHomework());
        model.addAttribute("sessionItem", session);
        return "user/user-hw.html";
    }
}
