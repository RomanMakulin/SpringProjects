package org.example.dto.user;

import lombok.Data;

@Data
public class UserUpdateDetails {
    private String name;
    private Long cashMoney;
    private int pin;
}
