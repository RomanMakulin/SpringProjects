package org.example.service;

import org.example.dto.user.UserCreateDetails;
import org.example.models.Card;
import org.example.models.Role;
import org.example.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserGenerateService {

    /**
     * Логика генерирования пользователей
     *
     * @return список рандомных пользователей
     */
    public List<User> loadUsers() {
        List<User> list = new ArrayList<>();
        String[] names = new String[]{"Roman", "Olga", "Oleg", "Max", "Petr", "Katy", "Liza", "Kirill"};
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(names.length);

            User user = new User(i, names[randomIndex], 100L, new Card(), "123", Role.ROLE_USER);
            user.getCard().setId(i);
            user.getCard().setCardMoney(100L);
            user.getCard().setPin(123);
            list.add(user);
        }
        return list;
    }

}
