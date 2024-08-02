package com.alexanderc.workoutapp.model;

import com.alexanderc.workoutapp.entity.WorkoutEntity;
import lombok.Data;


import java.util.List;

@Data
public class User {
    private String email;
    private String password;
    private String name;
}
