package org.example.dto.user;

import lombok.Data;

@Data
public class UserCreateDetails {
    private int id;
    private String name;
    private int pin;
    private int cashMoney;
    private String password;
}
