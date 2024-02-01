package com.example.TramsRest.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "trams")
public class Tram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roads_id", referencedColumnName = "id")
    private Road road;
}
