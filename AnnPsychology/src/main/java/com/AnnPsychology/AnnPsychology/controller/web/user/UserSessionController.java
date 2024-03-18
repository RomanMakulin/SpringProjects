package com.AnnPsychology.AnnPsychology.controller.web.user;

import com.AnnPsychology.AnnPsychology.services.user.iUserDetailsService;
import com.AnnPsychology.AnnPsychology.services.user.iUserSessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/sign-up-session")
    public String signUpSessionForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        return "user/go-session.html";
    }

    @GetMapping("/repeat-sign-up-session")
    public String repeatSignUpSessionForm(Model model) {
        model.addAttribute("user", userDetailsService.getAuthUser());
        model.addAttribute("errorMessage", "Дата недоступна, возможно она уже занята!");
        return "user/error-date.html";
    }

    @PostMapping("/sign-up-session/{id}")
    public String signUpSession(@PathVariable("id") Long id,
                                @ModelAttribute("date") LocalDate date,
                                @ModelAttribute("time") LocalTime time) {

        if (date == null | time == null) return "redirect:/user/repeat-sign-up-session";
        return userSessionService.signUpSession(id, date, time) ? "redirect:/user" : "redirect:/user/repeat-sign-up-session";
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
