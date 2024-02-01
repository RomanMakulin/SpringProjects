package com.example.TramsRest.controllers.web;

import com.example.TramsRest.models.Tram;
import com.example.TramsRest.service.TramService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/web")
public class TramWebController {
    /**
     * Сервис-интерфейс взаимодействия с БД
     */
    private final TramService tramService;

    /**
     * Получение главной страницы со всеми маршрутами
     *
     * @param model модель для связи с шаблонизатором
     * @return index.html
     */
    @GetMapping
    public String getIndexForm(Model model) {
        model.addAttribute("trams", tramService.getAllTrams());
        return "index";
    }

    @GetMapping("/create-tram")
    public String getFormCreate(Tram tram){
        return "create-tram";
    }

    @PostMapping("/tram-create")
    public String createTram(Tram tram){
        tramService.createTram(tram);
        return "redirect:/web";
    }
}
