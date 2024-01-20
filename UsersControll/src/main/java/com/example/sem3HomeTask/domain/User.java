package com.example.sem3HomeTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private int id;
    private String firstName;
    private int age;
    private String email;
}
