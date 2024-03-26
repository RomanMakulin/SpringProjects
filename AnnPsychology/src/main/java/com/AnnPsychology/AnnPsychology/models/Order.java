package com.AnnPsychology.AnnPsychology.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pay_status")
    private String payStatus;
    @Column(name = "pay_id")
    private String payID;
    @Column(name = "open_date")
    private LocalDateTime sessionDate;

    public Order(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }
}
