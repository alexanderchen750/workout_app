package com.alexanderc.workoutapp.model;

import com.alexanderc.workoutapp.entity.WorkoutEntity;


import java.util.List;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private List<WorkoutEntity> workouts;
}
