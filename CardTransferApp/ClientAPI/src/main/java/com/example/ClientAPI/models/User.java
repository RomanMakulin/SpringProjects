package com.example.ClientAPI.models;

import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class User {
    private Long id;
    private String username;
    private Long cashMoney;
    private Card card;
    private String password;
    private Role role;


    @ModelAttribute("username")
    public String getUsername() {
        return this.username;
    }
}
