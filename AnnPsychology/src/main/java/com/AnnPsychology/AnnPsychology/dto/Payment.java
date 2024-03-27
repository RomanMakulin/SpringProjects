package com.AnnPsychology.AnnPsychology.dto;

import com.AnnPsychology.AnnPsychology.models.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {
    private Amount amount;
    private boolean capture;
    private Confirmation confirmation;
    private String description;

    public Payment(BigDecimal price, String currency, String description) {
        this.amount = new Amount(price, currency);
        this.capture = true;
        this.confirmation = new Confirmation("redirect", "https://ann-novikova.ru/user");
        this.description = description;
    }
}
