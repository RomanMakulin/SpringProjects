package com.AnnPsychology.AnnPsychology.controller.web.admin;

import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
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
import java.util.List;

/**
 * Контроллер администратора (сессии)
 */
@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class SessionManageController {
    /**
     * Интерфейс управления сессиями
     */
    private final iAdminSessionService adminSessionService;

    /**
     * Интерфейс управления пользователями
     */
    private final iAdminUserService adminUserService;

    /**
     * Подключение стилей
     *
     * @return css
     */
    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style_admin.css";
    }

    /**
     * Запрос на получение главной страницы админа
     * Со списком всех сессий
     *
     * @param model модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("sessions", adminSessionService.getAllSessions());
        return "admin/admin.html";
    }

    /**
     * Запрос на вызов формы редактирования сессии
     *
     * @param model модель взаимодействия с шаблонизатором
     * @param id    ID сессии
     * @return html
     */
    @GetMapping("/edit-session-form/{id}")
    public String editForm(Model model,
                           @PathVariable("id") Long id) {
        model.addAttribute("sess", adminSessionService.getSessionById(id, adminSessionService.getAdapterRepository()));
        model.addAttribute("user", adminSessionService.getUserBySessionId(id, adminSessionService.getAdapterRepository()));
        return "admin/admin_user_update.html";
    }

    /**
     * Запрос на редактирование сессии
     *
     * @param id   ID сессии
     * @param date дата
     * @param time время
     * @param link ссылка
     * @return html главной страницы админа
     */
    @PostMapping("/edit-session/{id}")
    public String editSession(@PathVariable("id") Long id,
                              @ModelAttribute("date") LocalDate date,
                              @ModelAttribute("time") LocalTime time,
                              @ModelAttribute("link") String link) {
        adminSessionService.editSessionDateByAdmin(id, date, time);
        return "redirect:/admin";
    }

    /**
     * Запрос на смену ссылки ДЗ у сессии
     *
     * @param id   ID сессии
     * @param link ссылка на ДЗ в сессии
     * @return html главной страницы админа
     */
    @PostMapping("/change-link/{id}")
    public String changeSessionLink(@PathVariable("id") Long id,
                                    @ModelAttribute("sessionLink") String link) {
        adminSessionService.giveSessionLink(id, link);
        return "redirect:/admin";
    }

    /**
     * Запрос на отмену сессии по ID
     *
     * @param id ID сессии
     * @return html главной страницы админа
     */
    @GetMapping("/cancel/{id}")
    public String cancelSession(@PathVariable("id") Long id) {
        adminSessionService.cancelSession(id);
        return "redirect:/admin";
    }

    /**
     * Запрос на получение завершенных сессий
     *
     * @param model модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping("/latest")
    public String latestSessionList(Model model) {
        List<Session> withoutHomeWork = adminSessionService.getLatestWithoutHW();

        model.addAttribute("latestSession", withoutHomeWork);
        model.addAttribute("users", adminUserService.getAllUsers());
        model.addAttribute("isEmpty", withoutHomeWork.isEmpty());
        return "admin/admin_latest.html";
    }

    /**
     * Запрос на выдачу ДЗ по ID сессии
     *
     * @param id              ID сессии
     * @param sessionHomework ДЗ
     * @return html страницы со списком завершенных сессий
     */
    @PostMapping("/give-hw/{id}")
    public String giveHomework(@PathVariable("id") Long id,
                               @ModelAttribute("sessionHomework") String sessionHomework) {

        adminSessionService.giveSessionHomeWork(id, sessionHomework);
        return "redirect:/admin/latest";
    }
}
