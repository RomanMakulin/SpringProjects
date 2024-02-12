package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
@AllArgsConstructor
public class User {
    private int id;
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
