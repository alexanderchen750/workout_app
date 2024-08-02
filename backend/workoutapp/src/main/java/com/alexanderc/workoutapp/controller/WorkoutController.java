package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.model.IdResp;
import com.alexanderc.workoutapp.model.Workout;
import com.alexanderc.workoutapp.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/{userId}")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/workout")
    public ResponseEntity<IdResp> createWorkout(@PathVariable Long userId ){
        Long workoutId = workoutService.createWorkout(userId);
        // Return a ResponseEntity with status 201 Created and a body containing the userId
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdResp(workoutId));
    }

    @GetMapping("/workout")
    public ResponseEntity<List<Long>> getWorkoutIdsByUserId(@PathVariable Long userId) {
        List<Long> workoutIds = workoutService.getWorkoutIdsByUserId(userId);
        return ResponseEntity.ok(workoutIds);
    }

    @DeleteMapping("/workout/{workoutId}")
    public DeleteResp deleteWorkout(@PathVariable Long workoutId) {
        System.out.println(workoutId);
        return workoutService.deleteWorkout(workoutId);
    }
}
