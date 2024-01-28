package com.example.NarutoApi.model;

import lombok.Data;

import java.util.List;

@Data
public class Character {
    /**
     * Список всех персонажей
     */
    private List<Characters> characters;
}
