package com.alexanderc.workoutapp.service;


import com.alexanderc.workoutapp.model.DeleteResp;

import java.util.List;

public interface WorkoutService {
    Long createWorkout(Long userId);
    List<Long> getWorkoutIdsByUserId(Long userId);
    DeleteResp deleteWorkout(Long workoutId);
}
