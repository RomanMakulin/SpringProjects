package com.example.ServerAPI.dto.card;

import lombok.Data;

@Data
public class TransferDetails {
    private Long idSender;
    private Long idReciver;
    private Long moneyRecive;
    private int pin;
}
