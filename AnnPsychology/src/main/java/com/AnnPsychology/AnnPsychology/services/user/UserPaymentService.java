package com.AnnPsychology.AnnPsychology.services.user;

import com.AnnPsychology.AnnPsychology.dto.PaymentStatus;
import com.AnnPsychology.AnnPsychology.models.Order;
import com.AnnPsychology.AnnPsychology.models.User;
import com.AnnPsychology.AnnPsychology.dto.Payment;
import com.AnnPsychology.AnnPsychology.dto.PaymentAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Data
public class UserPaymentService {

    private PaymentAnswer paymentAnswer;

    public void pay(User user, String idempotenceKey) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.yookassa.ru/v3/payments";

        HttpHeaders headers = headersSetManage(idempotenceKey);
        
        Payment payment = new Payment(user, "RUB", "Оплата онлайн сессии");
        String json = new ObjectMapper().writeValueAsString(payment);

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<PaymentAnswer> response = restTemplate.exchange(url, HttpMethod.POST, request, PaymentAnswer.class);

        this.paymentAnswer = response.getBody();
    }

    public void updatePayStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.yookassa.ru/v3/payments";
        
        HttpHeaders headers = new HttpHeaders();
        String urlAnswer = url + "/" + paymentAnswer.getID();

        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        // ResponseEntity<PaymentAnswer> response = restTemplate.exchange(urlAnswer, HttpMethod.GET, entity, PaymentAnswer.class);

        this.paymentAnswer = restTemplate.exchange(urlAnswer, HttpMethod.GET, entity, PaymentAnswer.class).getBody();
    }

    public String getUpdatedStatus(){
        updatePayStatus();
        return paymentAnswer.getStatus();
    }

    public HttpHeaders headersSetManage(String idempotenceKey){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");
        headers.set("Idempotence-Key", idempotenceKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    
}
