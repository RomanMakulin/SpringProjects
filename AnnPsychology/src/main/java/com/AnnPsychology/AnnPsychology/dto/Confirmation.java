package com.AnnPsychology.AnnPsychology.dto;

import lombok.Data;

@Data
public class Confirmation {
    private String type;
    private String confirmation_url;
    private String return_url;

     public Confirmation(String type, String return_url) {
        this.type = type;
        this.return_url = return_url;
    }
    
}
