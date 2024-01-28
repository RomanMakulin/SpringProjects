package com.example.NarutoApi.controller;

import com.example.NarutoApi.model.Character;
import com.example.NarutoApi.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerApi {
    /**
     * Сервис по работе с characters
     */
    @Autowired
    private ServiceApi serviceApi;

    /**
     * Запрос получения всех characters
     *
     * @return статус выполнения запроса
     */
    @GetMapping("/api")
    public ResponseEntity<Character> getAllCharacters() {
        return new ResponseEntity<>(serviceApi.getCharacter(), HttpStatus.OK);
    }
}
