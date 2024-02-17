package com.example.ServerAPI.services.integrationLog;

import com.example.ServerAPI.dto.card.TransferDetails;

public interface iLogToFile {
    void withdrawReceiveLog(Long id, String userName, Long userCardMoney);
    void transferLog(TransferDetails transferDetails, Long id, String userNameSender, String userNameReceiver);
}
