package com.example.ClientAPI.dto.user;

import lombok.Data;

@Data
public class UserUpdateDetails {
    private String name;
    private Long cashMoney;
    private int pin;
}
