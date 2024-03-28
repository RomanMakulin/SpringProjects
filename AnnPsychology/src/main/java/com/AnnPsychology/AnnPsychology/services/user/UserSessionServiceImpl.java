package com.AnnPsychology.AnnPsychology.services.user;


import com.AnnPsychology.AnnPsychology.models.Order;
import com.AnnPsychology.AnnPsychology.models.Session;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.enums.SessionStatus;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.AnnPsychology.AnnPsychology.services.SessionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserSessionServiceImpl extends SessionServiceImpl implements iUserSessionService {

    /**
     * Адаптер для взаимодействия с базой данных
     */
    @Autowired
    private final AdapterRepository adapterRepository;

    /**
     * Платежный сервис
     */
    @Autowired
    private UserPaymentService paymentService;

    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    /**
     * Ограничитель дней для отмены сессий
     */
    private final long daysForCancel = 1; // поправить на часы (24 часа)

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

    /**
     * Создать новую сессию
     */
    @Override
    public void createNewSession() {
        User user = customUserDetailsServiceImpl.getAuthUser();
        Session session = new Session(user, user.getOrder().getSessionDate());
        adapterRepository.getSessionsRepository().save(session);
        user.setOrder(null);
        adapterRepository.getUserRepository().save(user);
    }

    @Override
    public String reserveSession(Long dateID) throws JsonProcessingException {
        SessionDate sessionDate = adapterRepository.getDateRepository().findById(dateID).orElseThrow();
        User user = customUserDetailsServiceImpl.getAuthUser();
        
        user.setOrder(new Order(sessionDate.getSessionDate()));
        paymentService.pay(user.checkPrice());
        user.getOrder().setPayID(paymentService.getPaymentAnswer().getId());
        sessionDate.setOpen(false);

        adapterRepository.getOrderRepository().save(user.getOrder());
        adapterRepository.getDateRepository().save(sessionDate);

        return paymentService.getPaymentAnswer().getConfirmation().getConfirmation_url();
    }


    /**
     * Получение всех сессий пользователя в отсортированном порядке
     * В реализации пользователя
     *
     * @return Список сессий
     */
    @Override
    public List<Session> getAllSessions() {
        List<Session> allSessions = customUserDetailsServiceImpl.getAuthUser().getSessionList();
        User user = customUserDetailsServiceImpl.getAuthUser();
        checkPay();
        
       // if (user.getOrder() != null) {
       //     String payStatus = paymentService.getUpdatedStatus(user.getOrder());
      //      if (payStatus.equals("succeeded")) createNewSession();
      //      else if (payStatus.equals("canceled")) paymentService.cancelPay();
      //  }
        
        return getAllSessionsAbstract(customUserDetailsServiceImpl.getAuthUser().getSessionList(), adapterRepository);
    }

    public void checkPay(){
        try {
            String payStatus = paymentService.getUpdatedStatus(user.getOrder());
            if (payStatus.equals("succeeded")) createNewSession();
            else if (payStatus.equals("canceled")) paymentService.cancelPay();
        } catch (NullPointerException e) e.printStackTrace();
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

    /**
     * Удаление всех дат (прошелших сессий) из базы данных
     */
    public void deleteLastSessionDate() {
        adapterRepository.getDateRepository().findAll().stream().toList().forEach(i -> {
            if (i.getSessionDate().isBefore(LocalDateTime.now()))
                adapterRepository.getDateRepository().delete(i);
        });
    }
}
