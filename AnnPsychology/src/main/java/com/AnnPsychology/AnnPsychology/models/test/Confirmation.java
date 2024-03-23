package com.AnnPsychology.AnnPsychology.models.test;

import lombok.Data;

@Data
public class Confirmation {
    private String type;
    private String confirmation_url;
    private String return_url;
}
