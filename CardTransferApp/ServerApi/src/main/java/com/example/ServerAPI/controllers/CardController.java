package com.example.ServerAPI.controllers;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;
import com.example.ServerAPI.services.CardServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
