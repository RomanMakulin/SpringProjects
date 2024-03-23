package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.models.test.Amount;
import com.AnnPsychology.AnnPsychology.models.test.PaymentForAPI;
import com.AnnPsychology.AnnPsychology.models.test.PaymentStatusForAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class Payment {
    public PaymentStatusForAPI pay(User user) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.yookassa.ru/v3/payments";
        PaymentForAPI payment = new PaymentForAPI();
        payment.setAmount(new Amount(user.getPrice(), "RUB"));
        payment.setCapture(true);
        payment.setConfirmation("redirect", "https://ann-novikova.ru/shop-verification-5Pvg3Jkv09.txt");
        payment.setDescription("Оплата сессии");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(payment);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");
        headers.set("Idempotence-Key", "17edd6235d"); // настроить генерацию ключа рандомно
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<>(json, headers);

        ResponseEntity<PaymentStatusForAPI> response =
                restTemplate.exchange(url, HttpMethod.POST, request, PaymentStatusForAPI.class);

        System.out.println(response.getBody().getConfirmation().getConfirmation_url());
        return response.getBody();
    }
}
