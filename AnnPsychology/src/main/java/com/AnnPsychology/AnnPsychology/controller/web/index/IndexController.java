package com.AnnPsychology.AnnPsychology.controller.web.index;

import com.AnnPsychology.AnnPsychology.models.test.Amount;
import com.AnnPsychology.AnnPsychology.models.test.PaymentForAPI;
import com.AnnPsychology.AnnPsychology.models.test.PaymentStatusForAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/shop-verification-5Pvg3Jkv09.txt")
    public String test(){
        return "shop-verification-5Pvg3Jkv09.txt";
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
