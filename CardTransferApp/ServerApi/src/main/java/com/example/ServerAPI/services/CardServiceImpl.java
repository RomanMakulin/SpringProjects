package com.example.ServerAPI.services;

//import com.example.UsersCardTransfer.aspects.MyLog;

import com.example.ServerAPI.aspects.MyLog;
import com.example.ServerAPI.dto.card.ActionMoneyDetails;
import com.example.ServerAPI.dto.card.CardUpdateDetails;
import com.example.ServerAPI.dto.card.TransferDetails;
import com.example.ServerAPI.models.User;
import com.example.ServerAPI.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Data
@RequiredArgsConstructor
@Log
public class CardServiceImpl implements iCardService {
    /**
     * Управление пользователями в БД через Spring data JPA
     */
    private final UserRepository userRepository;

    /**
     * Логика пополнения карты из наличных
     *
     * @param actionMoneyDetails аргументы из тела запроса
     * @return результирующее сообщение
     */
    @Override
    @Transactional
    @MyLog
    public void receiveMoney(ActionMoneyDetails actionMoneyDetails, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(id + " not found id"));

        if (actionMoneyDetails.getPin() == user.getCard().getPin()
                && user.getCashMoney() >= actionMoneyDetails.getMoney()) {
            user.setCashMoney(user.getCashMoney() - actionMoneyDetails.getMoney());
            Long resultMoneyOnCard = user.getCard().getCardMoney() + actionMoneyDetails.getMoney();
            user.getCard().setCardMoney(resultMoneyOnCard);
            userRepository.save(user);
        }
    }

    /**
     * Логика снятия денег с карты в наличные
     *
     * @param actionMoneyDetails аргументы из тела запроса
     * @return результирующее сообщение
     */
    @Override
    @Transactional
    @MyLog
    public void withdrawMoney(ActionMoneyDetails actionMoneyDetails, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(id + " not found id"));

        if (actionMoneyDetails.getPin() == user.getCard().getPin()
                && user.getCard().getCardMoney() >= actionMoneyDetails.getMoney()) {

            Long cardWithdraw = user.getCard().getCardMoney() - actionMoneyDetails.getMoney();
            Long newCash = user.getCashMoney() + actionMoneyDetails.getMoney();

            user.getCard().setCardMoney(cardWithdraw);
            user.setCashMoney(newCash);
            userRepository.save(user);
        }
    }

    /**
     * Логика перевода денег другому пользователю
     *
     * @param transferDetails аргументы из тела запроса
     * @return результирующее сообщение
     */
    @Override
    @Transactional
    @MyLog
    public void transferMoney(TransferDetails transferDetails, Long id) {

        User reciever = userRepository.findById(transferDetails.getIdReciver()).orElseThrow(() ->
                new NoSuchElementException(transferDetails.getIdReciver() + " not found id"));

        User sender = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(id + " not found id"));

        if (sender.getCard().getPin() == transferDetails.getPin()
                && sender.getCard().getCardMoney() >= transferDetails.getMoneyRecive()) {
            sender.getCard().setCardMoney(sender.getCard().getCardMoney() - transferDetails.getMoneyRecive());
            reciever.getCard().setCardMoney(reciever.getCard().getCardMoney() + transferDetails.getMoneyRecive());

            userRepository.save(reciever);
            userRepository.save(sender);
        }
    }

    /**
     * Логика обновления-изменения пин кода карты
     *
     * @param cardUpdateDetails передаваемые данные от клиента
     * @param id                уникальный идентификатор
     */
    @Override
    public void changePin(CardUpdateDetails cardUpdateDetails, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.getCard().setPin(cardUpdateDetails.getPin());
        userRepository.save(user);
    }
}
