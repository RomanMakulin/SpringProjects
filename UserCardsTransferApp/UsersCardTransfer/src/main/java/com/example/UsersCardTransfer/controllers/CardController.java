package com.example.UsersCardTransfer.controllers;

import com.example.UsersCardTransfer.dto.card.ActionMoneyDetails;
import com.example.UsersCardTransfer.dto.card.TransferDetails;
import com.example.UsersCardTransfer.services.CardServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/api")
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
    @PostMapping("/recieve")
    public ResponseEntity<String> recieveMoney(@RequestBody ActionMoneyDetails actionMoneyDetails) {
        return new ResponseEntity<>(cardService.recieveMoney(actionMoneyDetails), HttpStatus.OK);
    }

    /**
     * Снятие денег с карточки в наличку
     *
     * @param actionMoneyDetails аргументы из тела запроса
     * @return обновленные данные
     */
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestBody ActionMoneyDetails actionMoneyDetails) {
        return new ResponseEntity<>(cardService.withdrawMoney(actionMoneyDetails), HttpStatus.OK);
    }

    /**
     * Перевод денег с карты на карту другому человеку по его id
     *
     * @param transferDetails аргументы из тела запроса
     * @return обновленные данные
     */
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferDetails transferDetails) {
        return new ResponseEntity<>(cardService.transferMoney(transferDetails), HttpStatus.OK);
    }
}
