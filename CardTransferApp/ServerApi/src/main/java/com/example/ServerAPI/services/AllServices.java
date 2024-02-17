package com.example.ServerAPI.services;

import com.example.ServerAPI.services.integrationLog.IntegrationILog;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class AllServices {
    /**
     * Управление банковскими картами в БД
     */
    @Autowired
    private final CardServiceImpl cardService;

    /**
     * Управление пользователями в БД
     */
    @Autowired
    private final UserServiceImpl userService;

    /**
     * Логика записи в лог
     */
    @Autowired
    private IntegrationILog integrationLog;
}
