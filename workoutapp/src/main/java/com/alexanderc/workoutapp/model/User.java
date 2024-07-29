package com.alexanderc.workoutapp.model;

import lombok.Data;

@Data
public class User {
    private Long user_id;
    private String email;
    private String password;
    private String name;
}
