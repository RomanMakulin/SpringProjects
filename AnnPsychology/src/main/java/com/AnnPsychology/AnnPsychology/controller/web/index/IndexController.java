package com.AnnPsychology.AnnPsychology.controller.web.index;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер главной страницы
 */
@Controller
@Data
public class IndexController {

    /**
     * Переход на главную страницу
     *
     * @return html главнйо страницы
     */
    @GetMapping
    public String getIndex() {
        return "main/index.html";
    }

    /**
     * Внутри главной страницы переход на страницу "Обо мне"
     *
     * @return html страницы обо мне
     */
    @GetMapping("/about")
    public String getAbout() {
        return "main/about.html";
    }

    /**
     * Подключение файла стилей
     *
     * @return файл стилей
     */
    @GetMapping("/css/**")
    public String css() {
        return "../static/css/main.css";
    }
}
