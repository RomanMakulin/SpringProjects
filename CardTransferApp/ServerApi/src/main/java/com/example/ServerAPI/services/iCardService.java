package com.example.ServerAPI.services;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.TransferDetails;

public interface iCardService {
    String recieveMoney(ActionMoneyDetails actionMoneyDetails);
    String withdrawMoney(ActionMoneyDetails actionMoneyDetails);
    String transferMoney(TransferDetails transferDetails);
}
