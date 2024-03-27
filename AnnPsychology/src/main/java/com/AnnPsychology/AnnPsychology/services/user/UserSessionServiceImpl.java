package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.dto.Payment;
import com.AnnPsychology.AnnPsychology.dto.PaymentAnswer;
import com.AnnPsychology.AnnPsychology.models.Order;
import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.repository.SessionsRepository;
import com.AnnPsychology.AnnPsychology.services.SessionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


/**
 * Сервис для управления сессиями пользователя
 */
@EqualsAndHashCode(callSuper = true)
@Service
@Data
public class UserSessionServiceImpl extends SessionServiceImpl implements iUserSessionService {

    /**
     * Адаптер для взаимодействия с базой данных
     */
    @Autowired
    private final AdapterRepository adapterRepository;

    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    /**
     * Ограничитель дней для отмены сессий
     */
    private final long daysForCancel = 1; // поправить на часы (24 часа)

    @Autowired
    private UserPaymentService paymentService;

    /**
     * Удаление всех дат (прошелших сессий) из базы данных
     */
    public void deleteLastSessionDate() {
        adapterRepository.getDateRepository().findAll().stream().toList().forEach(i -> {
            if (i.getSessionDate().isBefore(LocalDateTime.now()))
                adapterRepository.getDateRepository().delete(i);
        });
    }

    /**
     * Получить список всех свободных окон (дат) для записи
     *
     * @return список дат
     */
    @Override
    public List<SessionDate> openSessionDateList() {
        deleteLastSessionDate();
        return adapterRepository.getDateRepository().findAll().stream()
                .filter(SessionDate::isOpen)
                .sorted(Comparator.comparing(SessionDate::getSessionDate)).toList();
    }

//    /**
//     * Создание новой сессии
//     *
//     * @param dateID нужная дата для регистрации сессии
//     */
//    @Override
//    public void createNewSession(Long dateID) {
//        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();
//        // User user = adapterRepository.getUserRepository().findById(customUserDetailsServiceImpl.getAuthUser().getId()).orElseThrow();
//        User user = customUserDetailsServiceImpl.getAuthUser();
//        Session session = new Session(user, sessionDate.getSessionDate());
//
//        sessionDate.setOpen(false);
//        adapterRepository.getDateRepository().save(sessionDate);
//        adapterRepository.getSessionsRepository().save(session);
//    }


    public void createNewSession(Order order) {
        User user = customUserDetailsServiceImpl.getAuthUser();
        Session session = new Session(user, order.getSessionDate());
        adapterRepository.getSessionsRepository().save(session);
        adapterRepository.getOrderRepository().delete(order);
    }

    @Override
    public String reserveSession(Long dateID) throws JsonProcessingException {

        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();

        Order order = new Order(sessionDate.getSessionDate());
        paymentService.pay(customUserDetailsServiceImpl.getAuthUser(), "id" + order.getId());

        order.setPayID(paymentService.getPaymentAnswer().getId());
        sessionDate.setOpen(false);

        adapterRepository.getOrderRepository().save(order);
        adapterRepository.getDateRepository().save(sessionDate);

        return paymentAnswer.getConfirmation().getConfirmation_url();

    }


    /**
     * Получение всех сессий пользователя в отсортированном порядке
     * В реализации пользователя
     *
     * @return Список сессий
     */
    @Override
    public List<Session> getAllSessions() {
        List<Session> allSessions = getAllSessionsAbstract(customUserDetailsServiceImpl.getAuthUser().getSessionList(), adapterRepository);
        allSessions.forEach(i -> {
            if (i.getUser().getOrder() != null) {
                String payStatus = paymentService.getUpdatedStatus();
                if (payStatus.equals("succeeded")) createNewSession(i.getUser().getOrder());
                else if (payStatus.equals("canceled")) paymentService.cancelPay(i.getSessionDate());
            }
        });
        return getAllSessionsAbstract(customUserDetailsServiceImpl.getAuthUser().getSessionList(), adapterRepository);
    }

    /**
     * Отменить сессию
     *
     * @param id ID сессии
     * @return логический результат выполнения метода
     */
    @Override
    public boolean cancelSession(Long id) {
        Session session = adapterRepository.getSessionsRepository().findById(id).orElseThrow();
        long daysDiff = ChronoUnit.DAYS.between(LocalDateTime.now(), session.getSessionDate()); // дни поменять на часы

        if (!(daysDiff >= daysForCancel)) return false;
        session.setSessionStatus(SessionStatus.SESSION_CANCELLED);

        SessionDate sessionDate1 = adapterRepository.getDateRepository().findAll().stream()
                .filter(i -> i.getSessionDate().equals(session.getSessionDate())).findAny().get();
        sessionDate1.setOpen(true);

        adapterRepository.getDateRepository().save(sessionDate1);
        return true;
    }

    /**
     * Получить домашнюю работу по конкретной сессии
     *
     * @param sessionID ID сессии
     * @return домашняя работа в строковом представлении
     */
    @Override
    public String getUserHomework(Long sessionID) {
        return getSessionById(sessionID, adapterRepository).getSessionHomework();
    }
}
