package com.AnnPsychology.AnnPsychology.models.test;

import lombok.Data;

@Data
public class PaymentForAPI {
    private Amount amount;
    private boolean capture;
    private Confirmation confirmation;
    private String description;

    public PaymentForAPI(User user, String currency, String description) {
        this.amount = new Amount(user.getPrice(), currency);
        this.capture = true;
        this.confirmation = new Confirmation("redirect", "https://ann-novikova.ru/user");
        this.description = description;
    }
}
