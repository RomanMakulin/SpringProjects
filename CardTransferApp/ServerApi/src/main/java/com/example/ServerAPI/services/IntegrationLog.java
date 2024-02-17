package com.example.ServerAPI.services;

import com.example.ServerAPI.dto.card.TransferDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Service
@RequiredArgsConstructor
public class IntegrationLog {
    /**
     * Spring Integration interface for write logs
     */
    @Autowired
    private final iFileGateway fileGateway;

    public void withdrawReceiveLog(Long id, String userName, Long userCardMoney) {
        // log with Spring Integration
        fileGateway.writeToFile("receive-log.txt", getParseDate() + " | New balance of user's (" + userName + ") bank card: " + userCardMoney);
    }

    public void transferLog(TransferDetails transferDetails, Long id, String userNameSender, String userNameReceiver) {
        // log with Spring Integration
        fileGateway.writeToFile("transfer-log.txt",
                getParseDate() + " | " + userNameSender + " successfully sent the money (" +
                        transferDetails.getMoneyRecive() + ") to " + userNameReceiver);
    }

    /**
     * Дата для записи логов
     */
    public String getParseDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu, HH:mm"));
    }
}
