package com.AnnPsychology.AnnPsychology.repository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Адаптер для взаимодействия трех таблиц из одного класса
 */
@Component
@RequiredArgsConstructor
@Data
public class AdapterRepository {
    /**
     * Взаимодействие с репозиторирем дат
     */
    private final DateRepository dateRepository;

    /**
     * Взаимодействие с репозиторирем сессий
     */
    private final SessionsRepository sessionsRepository;

    /**
     * Взаимодействие с репозиторирем пользователей
     */
    private final UserRepository userRepository;

    /**
     * Взаимодействие с репозиторирем заказов
     */
    private final OrderRepository orderRepository;
}
