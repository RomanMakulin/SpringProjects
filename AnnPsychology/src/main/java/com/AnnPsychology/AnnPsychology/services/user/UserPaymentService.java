package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.Order;
import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.dto.Payment;
import com.AnnPsychology.AnnPsychology.dto.PaymentAnswer;
import com.AnnPsychology.AnnPsychology.repository.AdapterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
@Data
@RequiredArgsConstructor
public class UserPaymentService {

    @Autowired
    private final AdapterRepository adapterRepository;

    @Autowired
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    private PaymentAnswer paymentAnswer;
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.yookassa.ru/v3/payments";

    public void pay(BigDecimal price) throws JsonProcessingException {

        HttpHeaders headers = headersSetManage();

        Payment payment = new Payment(price, "RUB", "Оплата онлайн сессии");
        String json = new ObjectMapper().writeValueAsString(payment);

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<PaymentAnswer> response = restTemplate.exchange(url, HttpMethod.POST, request, PaymentAnswer.class);

        System.out.println(response.getBody());

        this.paymentAnswer = response.getBody();
    }

    public void updatePayStatus(Order order) {
        HttpHeaders headers = new HttpHeaders();
        String urlAnswer = url + "/" + order.getPayID();

        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        // ResponseEntity<PaymentAnswer> response = restTemplate.exchange(urlAnswer, HttpMethod.GET, entity, PaymentAnswer.class);

        this.paymentAnswer = restTemplate.exchange(urlAnswer, HttpMethod.GET, entity, PaymentAnswer.class).getBody();
    }

    public String getUpdatedStatus(Order order) {
        updatePayStatus(order);
        return paymentAnswer.getStatus();
    }

    public HttpHeaders headersSetManage() {
        HttpHeaders headers = new HttpHeaders();
        StringBuffer result = new StringBuffer();

        Random random = new Random();
        for (int i = 0; i < 10; i++) result.append((char) (random.nextInt(25) + 'a'));

        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");
        headers.set("Idempotence-Key", result.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public void cancelPay() {
        User user = customUserDetailsServiceImpl.getAuthUser();
        SessionDate date = adapterRepository.getDateRepository().getBySessionDate(user.getOrder().getSessionDate());
        date.setOpen(true);
        adapterRepository.getDateRepository().save(date);
        adapterRepository.getOrderRepository().delete(user.getOrder());
    }

}
