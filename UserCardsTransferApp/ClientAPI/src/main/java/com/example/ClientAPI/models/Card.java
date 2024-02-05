package com.example.ClientAPI.models;

import lombok.Data;

@Data
public class Card {
    private Long id;
    private Integer pin;
    private Long cardMoney;
}
