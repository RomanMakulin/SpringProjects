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

    public PaymentAnswer pay(User user, String idempotenceKey) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.yookassa.ru/v3/payments";

        HttpHeaders headers = headersSetManage(idempotenceKey);
        
        Payment payment = new Payment(user, "RUB", "Оплата онлайн сессии");
        String json = new ObjectMapper().writeValueAsString(payment);

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<PaymentAnswer> response = restTemplate.exchange(url, HttpMethod.POST, request, PaymentAnswer.class);

        return response.getBody();
    }

//    public PaymentAnswer getAnswer(){
//        HttpEntity<String> request = new HttpEntity<>(json, headers);
//        ResponseEntity<PaymentAnswer> response = restTemplate.exchange(url, HttpMethod.POST, request, PaymentAnswer.class);
//
//        return response.getBody();
//    }

    public String checkPayStatus(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.yookassa.ru/v3/payments";
        
        HttpHeaders headers = new HttpHeaders();
        String urlAnswer = url + "/" + order.getPayID();

        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<PaymentStatus> response = restTemplate.exchange(urlAnswer, HttpMethod.GET, entity, PaymentStatus.class);

        return response.getBody().getStatus();
    }

    public HttpHeaders headersSetManage(String idempotenceKey){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("352122", "test_R9DnTTLp0AJ5mth_es0DTrnGgeQOQIH7320XtzesxnI");
        headers.set("Idempotence-Key", idempotenceKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    
}
