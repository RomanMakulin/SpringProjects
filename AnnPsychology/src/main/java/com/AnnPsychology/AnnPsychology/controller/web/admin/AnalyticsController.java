package com.AnnPsychology.AnnPsychology.controller.web.admin;

import com.AnnPsychology.AnnPsychology.services.admin.iAdminAnalyticsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер администратора (аналитика)
 */
@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/admin")
public class AnalyticsController {

    /**
     * Интерфейс логики по расчету аналитики
     */
    private final iAdminAnalyticsService analyticsService;

    /**
     * Запрос на получения списка всей аналитики на странице
     *
     * @param model модель взаимодействия с шаблонизатором
     * @return html
     */
    @GetMapping("/analytics")
    public String getAnalytics(Model model) {
        model.addAttribute("stats", analyticsService);
        return "admin/admin_statistics.html";
    }
}
