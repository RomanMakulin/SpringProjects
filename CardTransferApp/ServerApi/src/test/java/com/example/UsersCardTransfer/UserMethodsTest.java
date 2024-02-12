package com.example.UsersCardTransfer;

import com.example.ServerAPI.models.Card;
import com.example.ServerAPI.models.User;
import com.example.ServerAPI.repository.UserRepository;
import com.example.ServerAPI.services.CardServiceImpl;
import com.example.ServerAPI.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserMethodsTest {
    @Mock
    public UserRepository userRepository;

    @InjectMocks
    public CardServiceImpl cardService;

    @InjectMocks
    public UserServiceImpl userService;

    public List<User> fillUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("Roman");
        user1.setId(1L);
        user1.setCard(new Card());
        user1.getCard().setPin(123);
        user1.getCard().setCardMoney(1000L);
        user1.setCashMoney(100L);

        User user2 = new User();
        user2.setUsername("Olga");
        user2.setId(2L);
        user2.setCard(new Card());
        user2.getCard().setPin(123);
        user2.getCard().setCardMoney(1000L);
        user2.setCashMoney(100L);

        users.add(user1);
        users.add(user2);
        return users;
    }

    /**
     * TEST на получение пользователя по ID
     */
    @Test
    public void getUsersByIdTest() {
        List<User> userList = fillUsers();
        User user1 = userList.get(0);

        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        Assertions.assertEquals(userRepository.findById(user1.getId()).get(), userService.getById(user1.getId()).get());

    }

    /**
     * TEST на изменение имени пользователя
     */
    @Test
    public void changeNameTest() {

        List<User> listUsers = fillUsers();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(listUsers.get(0)));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(listUsers.get(1)));

        User user = userService.getById(1L).get();
        User userDetails = userService.getById(2L).get();

        userService.updateNameUser(userDetails, user.getId());

        Assertions.assertEquals(user.getUsername(), userDetails.getUsername());

    }

    /**
     * TEST на обновление текущего пользователя
     */
    @Test
    public void updateUserTest() {
        List<User> listUsers = fillUsers();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(listUsers.get(0)));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(listUsers.get(1)));

        User user = userService.getById(1L).get();
        User userDetails = userService.getById(2L).get();

        Mockito.when(userRepository.save(userDetails)).thenReturn(userDetails);
        user = userService.updateUser(userDetails);

        Assertions.assertEquals(user, userDetails);
    }

    /**
     * TEST на удаление пользователя
     */
    @Test
    public void deleteUserTest() {
        List<User> listUsers = fillUsers();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(listUsers.get(0)));
        User user = userService.getById(1L).get();

        userService.deleteUser(user.getId());

        Assertions.assertFalse(userService.getById(1L).isEmpty());
    }

}
