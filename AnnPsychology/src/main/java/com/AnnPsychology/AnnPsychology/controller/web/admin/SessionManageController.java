package com.AnnPsychology.AnnPsychology.controller.web.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.services.admin.iAdminSessionService;
import com.AnnPsychology.AnnPsychology.services.admin.iAdminUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class SessionManageController {
    private final iAdminSessionService adminSessionService;
    private final iAdminUserService adminUserService;

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style_admin.css";
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("sessions", adminSessionService.getAllSessions());
        return "admin/admin.html";
    }

    @GetMapping("/edit-session-form/{id}")
    public String editForm(Model model,
                           @PathVariable("id") Long id) {
        model.addAttribute("sess", adminSessionService.getSessionById(id, adminSessionService.getAdapterRepository()));
        model.addAttribute("user", adminSessionService.getUserBySessionId(id, adminSessionService.getAdapterRepository()));

        return "admin/admin_user_update.html";
    }

    @PostMapping("/edit-session/{id}")
    public String editSession(@PathVariable("id") Long id,
                              @ModelAttribute("date") LocalDate date,
                              @ModelAttribute("time") LocalTime time,
                              @ModelAttribute("link") String link) {
        adminSessionService.editSessionDateByAdmin(id, date, time);
        return "redirect:/admin";
    }

    @PostMapping("/change-link/{id}")
    public String changeSessionLink(@PathVariable("id") Long id,
                                    @ModelAttribute("sessionLink") String link) {

        adminSessionService.giveSessionLink(id, link);
        return "redirect:/admin";
    }

    @GetMapping("/cancel/{id}")
    public String cancelSession(@PathVariable("id") Long id) {
        adminSessionService.cancelSession(id);
        return "redirect:/admin";
    }

    @GetMapping("/latest")
    public String latestSessionList(Model model) {
        List<Session> withoutHomeWork = adminSessionService.getLatestWithoutHW();

        model.addAttribute("latestSession", withoutHomeWork);
        model.addAttribute("users", adminUserService.getAllUsers());
        model.addAttribute("isEmpty", withoutHomeWork.isEmpty());
        return "admin/admin_latest.html";
    }

    @PostMapping("/give-hw/{id}")
    public String giveHomework(@PathVariable("id") Long id,
                               @ModelAttribute("sessionHomework") String sessionHomework) {

        adminSessionService.giveSessionHomeWork(id, sessionHomework);
        return "redirect:/admin/latest";
    }


//    another class
    @GetMapping("/calendar")
    public String calendar(Model model){
        model.addAttribute("closeDates", adminSessionService.getCalendarDatesList());
        return "admin/calendar.html";
    }

    @PostMapping("/calendar/new")
    public String openSession(@ModelAttribute("date") LocalDate date,
                              @ModelAttribute("time") LocalTime time){
        adminSessionService.calendarManage(LocalDateTime.of(date, time));
        return "redirect:/admin/calendar";
    }

}
