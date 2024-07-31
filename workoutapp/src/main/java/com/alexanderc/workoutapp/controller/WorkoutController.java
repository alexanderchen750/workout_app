package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.User;
import com.alexanderc.workoutapp.model.Workout;
import com.alexanderc.workoutapp.service.UserService;
import com.alexanderc.workoutapp.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts/")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/create/{userId}")
    public Long createWorkout(@PathVariable Long userId ){
        return workoutService.createWorkout(userId);
    }

    @GetMapping("/ids/{userId}")
    public ResponseEntity<List<Long>> getWorkoutIdsByUserId(@PathVariable Long userId) {
        List<Long> workoutIds = workoutService.getWorkoutIdsByUserId(userId);
        return ResponseEntity.ok(workoutIds);
    }
}
