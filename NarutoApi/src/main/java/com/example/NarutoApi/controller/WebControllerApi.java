package com.example.NarutoApi.controller;

import com.example.NarutoApi.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WEB контроллер
 */
@Controller
@RequestMapping("/web")
public class WebControllerApi {

    /**
     * Сервис для работы с базой данных (с character)
     */
    @Autowired
    private ServiceApi serviceApi;

    /**
     * Метод получения главной страницы с character
     *
     * @param model взаимодействие с шаблонизатором
     * @return возвращаем index.html
     */
    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("characters", serviceApi.getCharacter().getCharacters());
        return "index";
    }

}
