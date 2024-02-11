package com.example.ClientAPI.controller;

import com.example.ClientAPI.dto.card.ActionMoneyDetails;
import com.example.ClientAPI.dto.card.CardUpdateDetails;
import com.example.ClientAPI.dto.card.TransferDetails;
import com.example.ClientAPI.models.User;
import com.example.ClientAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/user")
public class WebUserController extends SpringBootServletInitializer {
    /**
     * Сервис управления внешними данными через OpenFeign
     */
    @Autowired
    private UserService userService;

    /**
     * Подключение собственных стилей
     *
     * @return путь к файлу стилей
     */
    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style.css";
    }

    /**
     * Получение профиля пользователя
     *
     * @param id    уникальный идентификатор пользователя
     * @param model связь с шаблонизатором
     * @return страница пользователя
     */
    @GetMapping("{id}")
    public String getUserProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getSingleUser(id));
        return "user";
    }

    /**
     * Изменение имени пользователю
     *
     * @param user пользователь
     * @return форма редактирования имени
     */
    @GetMapping("/update-name/{id}")
    public String getUpdateForm(User user) {
        return "update-name.html";
    }

    /**
     * Пополнение денег на карту
     *
     * @param actionMoneyDetails данные для выполнения операции
     * @param user               пользователь
     * @return форма операции
     */
    @GetMapping("/receive/{id}")
    public String receiveMoney(ActionMoneyDetails actionMoneyDetails, User user) {
        return "receive";
    }

    /**
     * Снятие денег с карты
     *
     * @param actionMoneyDetails данные для выполнения операции
     * @param user               пользователь
     * @return форма операции
     */
    @GetMapping("/withdraw/{id}")
    public String withdrawMoney(ActionMoneyDetails actionMoneyDetails, User user) {
        return "withdraw";
    }

    /**
     * Перевод денег с карты на карту другого человека
     *
     * @param transferDetails данные для выполнения операции
     * @param user            пользователь
     * @return форма операции
     */
    @GetMapping("/transfer/{id}")
    public String transferMoney(TransferDetails transferDetails, User user) {
        return "transfer";
    }

    /**
     * Изменение логина
     *
     * @param cardUpdateDetails данные для выполнения операции
     * @param user              пользователь
     * @return форма операции
     */
    @GetMapping("/changePin/{id}")
    public String transferMoney(CardUpdateDetails cardUpdateDetails, User user) {
        return "changePin";
    }
}
