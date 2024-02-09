package com.example.ServerAPI.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long cashMoney = 100L;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;
    private Role role;
    private String password;

//    public User(String username) {
//        this.username = username;
//        this.card = new Card();
//        this.card.setPin(123);
//        this.card.setCardMoney(1000L);
//        this.role = Role.ROLE_USER;
//    }
}
