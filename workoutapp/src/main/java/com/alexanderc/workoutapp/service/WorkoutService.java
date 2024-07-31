package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.model.Workout;

import java.util.List;

public interface WorkoutService {
    Long createWorkout(Long userId);
    List<Long> getWorkoutIdsByUserId(Long userId);
}
