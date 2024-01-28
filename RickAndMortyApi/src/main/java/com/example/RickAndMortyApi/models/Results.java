package com.example.RickAndMortyApi.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Results {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private LocalDateTime created;
    private Location location;
}
