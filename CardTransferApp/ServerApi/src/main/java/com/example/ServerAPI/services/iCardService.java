package com.example.ServerAPI.services;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;

public interface iCardService {
    void receiveMoney(ActionMoneyDetails actionMoneyDetails, Long id);

    void withdrawMoney(ActionMoneyDetails actionMoneyDetails, Long id);

    void transferMoney(TransferDetails transferDetails, Long id);

    void changePin(CardUpdateDetails cardUpdateDetails, Long id);
}
