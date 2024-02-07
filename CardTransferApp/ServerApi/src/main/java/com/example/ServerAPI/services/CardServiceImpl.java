package com.example.ServerAPI.services;

//import com.example.UsersCardTransfer.aspects.MyLog;
import com.example.ServerAPI.aspects.MyLog;
import com.example.ServerAPI.dto.card.ActionMoneyDetails;
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
    public String recieveMoney(ActionMoneyDetails actionMoneyDetails) {
        User user = userRepository.findById(actionMoneyDetails.getIdUser()).orElseThrow(() ->
                new NoSuchElementException(actionMoneyDetails.getIdUser() + " not found id"));

        if (actionMoneyDetails.getPin() == user.getCard().getPin()
                && user.getCashMoney() >= actionMoneyDetails.getMoney()) {
            user.setCashMoney(user.getCashMoney() - actionMoneyDetails.getMoney());
            Long resultMoneyOnCard = user.getCard().getCardMoney() + actionMoneyDetails.getMoney();
            user.getCard().setCardMoney(resultMoneyOnCard);
            userRepository.save(user);
            return "Успешно! Текущий баланс карты: " + resultMoneyOnCard;
        }
        return "something wrong!";
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
    public String withdrawMoney(ActionMoneyDetails actionMoneyDetails) {
        User user = userRepository.findById(actionMoneyDetails.getIdUser()).orElseThrow(() ->
                new NoSuchElementException(actionMoneyDetails.getIdUser() + " not found id"));

        if (actionMoneyDetails.getPin() == user.getCard().getPin()
                && user.getCard().getCardMoney() >= actionMoneyDetails.getMoney()) {

            Long cardWithdraw = user.getCard().getCardMoney() - actionMoneyDetails.getMoney();
            Long newCash = user.getCashMoney() + actionMoneyDetails.getMoney();

            user.getCard().setCardMoney(cardWithdraw);
            user.setCashMoney(newCash);
            userRepository.save(user);
            return "Успешно! Текущий баланс карты: " + cardWithdraw;
        }
        return "something wrong!";
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
    public String transferMoney(TransferDetails transferDetails) {

        User reciever = userRepository.findById(transferDetails.getIdReciver()).orElseThrow(() ->
                new NoSuchElementException(transferDetails.getIdReciver() + " not found id"));

        User sender = userRepository.findById(transferDetails.getIdSender()).orElseThrow(() ->
                new NoSuchElementException(transferDetails.getIdSender() + " not found id"));

        if (sender.getCard().getPin() == transferDetails.getPin()
                && sender.getCard().getCardMoney() >= transferDetails.getMoneyRecive()) {
            sender.getCard().setCardMoney(sender.getCard().getCardMoney() - transferDetails.getMoneyRecive());
            reciever.getCard().setCardMoney(reciever.getCard().getCardMoney() + transferDetails.getMoneyRecive());

            userRepository.save(reciever);
            userRepository.save(sender);
            return "Успешно! Перевод в размере " + transferDetails.getMoneyRecive() + " рублей поступил.";
        }

        return "something wrong!";
    }
}
