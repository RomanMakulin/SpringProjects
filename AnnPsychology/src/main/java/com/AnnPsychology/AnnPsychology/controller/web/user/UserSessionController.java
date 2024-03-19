package com.AnnPsychology.AnnPsychology.controller.web.user;

import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.services.user.iUserDetailsService;
import com.AnnPsychology.AnnPsychology.services.user.iUserSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserSessionController {
    private final iUserDetailsService userDetailsService;
    private final iUserSessionService userSessionService;

    @GetMapping
    public String allSessions(Model model) {
        model.addAttribute("userSessions", userSessionService.getAllSessions()); // TO DO: передать список сессий авторизованного юзера userDetailsService.getAuthUser()...
        model.addAttribute("user", userDetailsService.getAuthUser());
        return "user/user.html";
    }

    @GetMapping("/sign-up-session-form")
    public String signUpSessionForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("openDateList", userSessionService.openSessionDateList());
        return "user/go-session.html";
    }

    @PostMapping("/sign-up-session/{id}")
    public String signUpSession(@PathVariable("id") Long dateID) {
        userSessionService.signUpSession(dateID);
        return "redirect:/user";
    }

    @GetMapping("/repeat-sign-up-session")
    public String repeatSignUpSessionForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("errorMessage", "Дата недоступна, возможно она уже занята!");
        return "user/error-date.html";
    }

    /**
     * Отменить сессию и вернуть деньги
     *
     * @param id уникальный идентификатор пользователя
     * @return личный кабинет пользователя
     */
    @GetMapping("/cancel/{id}")
    public String cancelSession(@PathVariable("id") Long id, Model model) {
        if (userSessionService.cancelSession(id)) return "redirect:/user";
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("errorMessage", "Отмена сессии менее чем за сутки невозможна.");
        return "user/cancel-error.html";
    }
}
