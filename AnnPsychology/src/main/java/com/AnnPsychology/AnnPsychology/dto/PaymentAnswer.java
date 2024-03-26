package com.AnnPsychology.AnnPsychology.dto;

import lombok.Data;

@Data
public class PaymentAnswer {
    private String id;
    private String pending;
    private Amount amount;
    private String description;
    private Confirmation confirmation;
}
