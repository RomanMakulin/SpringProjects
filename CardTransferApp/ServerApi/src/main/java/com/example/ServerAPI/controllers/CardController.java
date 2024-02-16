package com.example.ServerAPI.controllers;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;
import com.example.ServerAPI.services.CardServiceImpl;
import com.example.ServerAPI.services.UserServiceImpl;
import com.example.ServerAPI.services.iFileGateway;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/server")
public class CardController {
    /**
     * Управление банковскими картами в БД
     */
    private final CardServiceImpl cardService;

    /**
     * Управление пользователями в БД
     */
    private final UserServiceImpl userService;

    /**
     * Spring Integration interface for write logs
     */
    private final iFileGateway fileGateway;

    /**
     * Дата для записи логов
     */
    public String getParseDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu, HH:mm"));
    }

    /**
     * Собственная метрика (счетчик переводов)
     */
    private final Counter requestCounter = Metrics.counter("transfer_counter");

    /**
     * Пополнить карточку
     *
     * @param actionMoneyDetails аргументы из тела запроса
     * @return перенаправление на страницу пользоваетля
     */
    @PostMapping("/receive/{id}")
    public RedirectView receiveMoney(@PathVariable("id") Long id, ActionMoneyDetails actionMoneyDetails) {

        cardService.receiveMoney(actionMoneyDetails, id);

        // data for log
        String userName = userService.getById(id).get().getUsername();
        Long userCardMoney = userService.getById(id).get().getCard().getCardMoney();

        // log with Spring Integration
        fileGateway.writeToFile("receive-log.txt", getParseDate() + " | New balance of user's (" + userName + ") bank card: " + userCardMoney);
        return new RedirectView("http://localhost:8765/main/user/" + id);
    }

    /**
     * Снятие денег с карточки в наличку
     *
     * @param actionMoneyDetails аргументы из тела запроса
     * @return перенаправление на страницу пользоваетля
     */
    @PostMapping("/withdraw/{id}")
    public RedirectView withdrawMoney(@PathVariable("id") Long id, ActionMoneyDetails actionMoneyDetails) { // Убрать RequestBody
        cardService.withdrawMoney(actionMoneyDetails, id);

        // data for log
        String userName = userService.getById(id).get().getUsername();
        Long userCardMoney = userService.getById(id).get().getCard().getCardMoney();

        // log with Spring Integration
        fileGateway.writeToFile("withdraw-log.txt", getParseDate() + " | New balance of user's (" + userName + ") bank card: " + userCardMoney);

        return new RedirectView("http://localhost:8765/main/user/" + id);
    }

    /**
     * Перевод денег с карты на карту другому человеку по его id
     *
     * @param transferDetails аргументы из тела запроса
     * @return перенаправление на страницу пользоваетля
     */
    @PostMapping("/transfer/{id}")
    public RedirectView transferMoney(@PathVariable("id") Long id, TransferDetails transferDetails) {
        cardService.transferMoney(transferDetails, id);
        requestCounter.increment();

        // data for log
        String userNameSender = userService.getById(id).get().getUsername();
        String userNameReceiver = userService.getById(transferDetails.getIdReciver()).get().getUsername();
        Long receiveMoney = transferDetails.getMoneyRecive();

        // log with Spring Integration
        fileGateway.writeToFile("transfer-log.txt",
                getParseDate() + " | " + userNameSender + " successfully sent the money (" + receiveMoney + ") to " + userNameReceiver);

        return new RedirectView("http://localhost:8765/main/user/" + id);
    }

    /**
     * Запрос на изменение пин кода карты
     *
     * @param id                уникальный идентификатор пользователя
     * @param cardUpdateDetails передаваемые данные
     * @return перенаправление на страницу пользоваетля
     */
    @PostMapping("/changePin/{id}")
    public RedirectView changePin(@PathVariable("id") Long id, CardUpdateDetails cardUpdateDetails) {
        cardService.changePin(cardUpdateDetails, id);
        return new RedirectView("http://localhost:8765/main/user/" + id);
    }
}
