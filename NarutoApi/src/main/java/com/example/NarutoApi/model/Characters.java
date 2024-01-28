package com.example.NarutoApi.model;

import lombok.Data;

/**
 * Класс персонажа
 */
@Data
public class Characters {
    private String id;
    private String name;
    private String[] images;
    private String[] jutsu;
    private String[] natureType;
    private String[] uniqueTraits;
}
