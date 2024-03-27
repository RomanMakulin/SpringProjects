package com.AnnPsychology.AnnPsychology.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentAnswer {
    private String id;
    private String status;
    private Amount amount;
    private String description;
    private Confirmation confirmation;
    private LocalDateTime created_at;
}
