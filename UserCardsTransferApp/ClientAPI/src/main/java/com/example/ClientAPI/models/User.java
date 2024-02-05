package com.example.ClientAPI.models;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Long cashMoney;
    private Card card;
}
