package com.example.ServerAPI.controllers;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;
import com.example.ServerAPI.services.AdapterService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/server")
public class CardController {

    /**
     * Адаптер: users service, card service, integrator
     */
    @Autowired
    private AdapterService adapterService;

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
        adapterService.getCardService().receiveMoney(actionMoneyDetails, id);

        // data for log
        String username = adapterService.getUserService().getById(id).get().getUsername();
        Long moneyCard = adapterService.getUserService().getById(id).get().getCard().getCardMoney();
        adapterService.getIntegrationLog().withdrawReceiveLog(id, username, moneyCard); // for Spring Integration

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
        adapterService.getCardService().withdrawMoney(actionMoneyDetails, id);

        // data for log
        String username = adapterService.getUserService().getById(id).get().getUsername();
        Long moneyCard = adapterService.getUserService().getById(id).get().getCard().getCardMoney();
        adapterService.getIntegrationLog().withdrawReceiveLog(id, username, moneyCard); // for Spring Integration

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
        adapterService.getCardService().transferMoney(transferDetails, id);
        requestCounter.increment(); // for Grafana metrics

        // for Spring Integration
        String nameSender = adapterService.getUserService().getById(id).get().getUsername();
        String nameReceiver = adapterService.getUserService().getById(transferDetails.getIdReciver()).get().getUsername();
        adapterService.getIntegrationLog().transferLog(transferDetails, id, nameSender, nameReceiver);

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
        adapterService.getCardService().changePin(cardUpdateDetails, id);
        return new RedirectView("http://localhost:8765/main/user/" + id);
    }
}
