package com.example.ServerAPI.controllers;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;
import com.example.ServerAPI.services.CardServiceImpl;
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
     * Пополнить карточку
     *
     * @param actionMoneyDetails аргументы из тела запроса
     * @return обновленные данные
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
     * @return обновленные данные
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
     * @return обновленные данные
     */
    @PostMapping("/transfer/{id}")
    public RedirectView transferMoney(@PathVariable("id") Long id, TransferDetails transferDetails) {
        cardService.transferMoney(transferDetails, id);
        return new RedirectView("http://localhost:8765/main/user/" + id);
    }

    @PostMapping("/changePin/{id}")
    public RedirectView changePin(@PathVariable("id") Long id, CardUpdateDetails cardUpdateDetails) {
        cardService.changePin(cardUpdateDetails, id);
        return new RedirectView("http://localhost:8765/main/user/" + id);
    }
}
