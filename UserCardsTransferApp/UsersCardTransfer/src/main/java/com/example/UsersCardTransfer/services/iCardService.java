package com.example.UsersCardTransfer.services;

import com.example.UsersCardTransfer.dto.card.ActionMoneyDetails;
import com.example.UsersCardTransfer.dto.card.TransferDetails;

public interface iCardService {
    String recieveMoney(ActionMoneyDetails actionMoneyDetails);
    String withdrawMoney(ActionMoneyDetails actionMoneyDetails);
    String transferMoney(TransferDetails transferDetails);
}
