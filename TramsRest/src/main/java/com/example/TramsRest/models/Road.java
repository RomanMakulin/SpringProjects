package com.example.TramsRest.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс-сущность "Маршрут"
 */
@Entity
@Table(name = "roads")
@Data
@NoArgsConstructor
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String startPoint;
    private String endPoint;
}
