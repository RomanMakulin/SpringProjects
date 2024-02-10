package com.example.ClientAPI.services;

import com.example.ClientAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class UsersTransferService {
    /**
     * Обертка для работы с запросами
     */
    @Autowired
    private RestTemplate template;

    /**
     * Управление http заголовкаими
     */
    @Autowired
    private HttpHeaders headers;

    /**
     * Получение всех users по внешнему Api
     *
     * @return Characters
     */

    /**
     * Получение всех пользователей по внешнему API
     *
     * @return список пользователей
     */
    public List<User> getAllUsers() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = template.exchange("http://127.0.0.1:8765/server", HttpMethod.GET, entity, List.class);
        return response.getBody();
    }

    /**
     * Получение одного пользователя
     *
     * @param id уникальный иднтификатор
     * @return пользователь
     */
    public User getUser(Long id) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<User> response = template.exchange("http://localhost:8765/server/" + id, HttpMethod.GET, entity, User.class);
        return response.getBody();
    }
}
