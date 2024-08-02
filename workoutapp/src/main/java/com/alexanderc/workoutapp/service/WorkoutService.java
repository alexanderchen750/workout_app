package com.alexanderc.workoutapp.service;


import java.util.List;

public interface WorkoutService {
    Long createWorkout(Long userId);
    List<Long> getWorkoutIdsByUserId(Long userId);
}
