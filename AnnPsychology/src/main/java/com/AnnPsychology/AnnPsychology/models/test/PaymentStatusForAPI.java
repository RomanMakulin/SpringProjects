package com.AnnPsychology.AnnPsychology.models.test;

import lombok.Data;

@Data
public class PaymentStatusForAPI {
    private String id;
    private String pending;
    private Amount amount;
    private String description;
    private Confirmation confirmation;
}
