package com.alexanderc.workoutapp.service;


import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.model.IdResp;
import com.alexanderc.workoutapp.model.WorkoutReq;
import com.alexanderc.workoutapp.model.WorkoutResp;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface WorkoutService {
    Long createWorkout(String email, WorkoutReq workoutReq);
    List<WorkoutResp> getWorkoutIdsByUserId(String email);
    DeleteResp deleteWorkout(String email, Long workoutId);
}
