package com.example.UsersCardTransfer;

import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;
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
public class CardsActionsTest {

    @Mock
    public UserRepository userRepository;

    @InjectMocks
    public CardServiceImpl cardService;

    @InjectMocks
    public UserServiceImpl userService;

    public List<User> fillUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setCard(new Card());
        user1.getCard().setPin(123);
        user1.getCard().setCardMoney(1000L);
        user1.setCashMoney(100L);

        User user2 = new User();
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
     * TEST на функционал изменения пин кода
     */
    @Test
    public void changePinTest() {
        List<User> userList = fillUsers();
        User user1 = userList.get(0);

        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        User mockedUser = userRepository.findById(user1.getId()).get();

        CardUpdateDetails cardUpdateDetails = new CardUpdateDetails();
        cardUpdateDetails.setPin(mockedUser.getCard().getPin());
        cardUpdateDetails.setPin(222);

        cardService.changePin(cardUpdateDetails, mockedUser.getId());

        Assertions.assertEquals(mockedUser.getCard().getPin(), 222);
    }

    /**
     * TEST на пополнение карты
     */
    @Test
    public void receiveTest() {
        List<User> userList = fillUsers();
        User user1 = userList.get(0);

        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        User mockedUser = userRepository.findById(user1.getId()).get();

        ActionMoneyDetails actionMoneyDetails = new ActionMoneyDetails();
        actionMoneyDetails.setPin(mockedUser.getCard().getPin());
        actionMoneyDetails.setMoney(100L);

        cardService.receiveMoney(actionMoneyDetails, mockedUser.getId());

        Assertions.assertEquals(mockedUser.getCashMoney(), 0);
        Assertions.assertEquals(mockedUser.getCard().getCardMoney(), 1100L);

    }

    /**
     * TEST на снятие денег с карты
     */
    @Test
    public void withdrawTest() {
        List<User> userList = fillUsers();
        User user1 = userList.get(0);

        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        User mockedUser = userRepository.findById(user1.getId()).get();

        ActionMoneyDetails actionMoneyDetails = new ActionMoneyDetails();
        actionMoneyDetails.setPin(mockedUser.getCard().getPin());
        actionMoneyDetails.setMoney(100L);

        cardService.withdrawMoney(actionMoneyDetails, mockedUser.getId());

        Assertions.assertEquals(mockedUser.getCashMoney(), 200L);
        Assertions.assertEquals(mockedUser.getCard().getCardMoney(), 900L);

    }

    /**
     * TEST на перевод средств другому пользователю
     */
    @Test
    public void transferMoneyTest() {
        List<User> userList = fillUsers();
        User user1 = userList.get(0);
        User user2 = userList.get(1);

        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));

        User firstUserForVerification = userService.getById(user1.getId()).get();
        User secondUserForVerification = userService.getById(user2.getId()).get();

        TransferDetails transferDetails = new TransferDetails();
        transferDetails.setIdReciver(secondUserForVerification.getId());
        transferDetails.setMoneyRecive(50L);
        transferDetails.setPin(123);

        cardService.transferMoney(transferDetails, firstUserForVerification.getId());

        Assertions.assertEquals(userRepository.findById(user1.getId()).get().getCard().getCardMoney(), 950);
        Assertions.assertEquals(secondUserForVerification.getCard().getCardMoney(), 1050);

    }

}
