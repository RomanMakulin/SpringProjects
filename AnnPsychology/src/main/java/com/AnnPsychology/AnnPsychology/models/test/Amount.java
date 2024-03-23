package com.AnnPsychology.AnnPsychology.models.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Amount {
    private BigDecimal value;
    private String currency;
}
