package com.example.ClientAPI.services;

import com.example.ClientAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
     * Ресурс Api
     */
    private static final String USERS_API = "http://127.0.0.1:8082/server";

    /**
     * Получение всех users по внешнему Api
     *
     * @return Characters
     */

//    public List<User> getAllUsers() {
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<List> response = template.exchange(USERS_API, HttpMethod.GET, entity, List.class);
//        return response.getBody();
//    }
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ArrayList> response = template.exchange(USERS_API, HttpMethod.GET, entity, ArrayList.class);

        if (response.getStatusCodeValue() == 200) {
            List<User> userList = response.getBody();
            users.addAll(userList);
        }

        return users;
    }

}
