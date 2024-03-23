package com.AnnPsychology.AnnPsychology.models.test;

import lombok.Data;

@Data
public class PaymentForAPI {
    private Amount amount;
    private boolean capture;
    private Confirmation confirmation;
    private String description;

    public void setConfirmation(String type, String return_url) {
        this.confirmation = new Confirmation();
        confirmation.setType(type);
        confirmation.setReturn_url(return_url);
    }
}
