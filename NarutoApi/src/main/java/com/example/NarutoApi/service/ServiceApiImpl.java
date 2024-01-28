package com.example.NarutoApi.service;

import com.example.NarutoApi.model.Character;
import com.example.NarutoApi.model.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceApiImpl implements ServiceApi {
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
    private static final String CHARACTER_API = "https://narutodb.xyz/api/character";

    /**
     * Метод получения character по заданному внешнему API
     * @return возвращает character со всеми его данными
     */
    @Override
    public Character getCharacter() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Character> response = template.exchange(CHARACTER_API, HttpMethod.GET, entity, Character.class);

        List<Characters> characters = Objects.requireNonNull(response.getBody()).getCharacters();
        characters.forEach(item -> {
            if (item.getImages().length == 0)
                item.setImages(new String[]{"https://cdni.iconscout.com/illustration/premium/thumb/404-7304110-5974976.png"});
        });
        return response.getBody();
    }

}
