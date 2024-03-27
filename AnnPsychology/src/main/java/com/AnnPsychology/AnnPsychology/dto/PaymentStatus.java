package com.AnnPsychology.AnnPsychology.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentStatus { // УДАЛИТЬ. PaymentAnswer - основа.
    private String id;
    private String status;
    private Amount amount;
    private LocalDateTime created_at;
}
