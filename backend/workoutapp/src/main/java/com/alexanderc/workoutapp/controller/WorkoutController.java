package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.*;
import com.alexanderc.workoutapp.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com/"})

    @PostMapping("/workout")
    public ResponseEntity<IdResp> createWorkout(@RequestBody WorkoutReq workoutReq ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long workoutId = workoutService.createWorkout(authentication.getName(), workoutReq);
        // Return a ResponseEntity with status 201 Created and a body containing the userId
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdResp(workoutId));
    }
    @GetMapping("/workout")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    public ResponseEntity<List<WorkoutResp>> getWorkoutIdsByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<WorkoutResp> workoutIds = workoutService.getWorkoutIdsByUserId(authentication.getName());
        return ResponseEntity.ok(workoutIds);
    }

    @DeleteMapping("/workout/{workoutId}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com/"})
    public DeleteResp deleteWorkout(@PathVariable Long workoutId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(workoutId);
        return workoutService.deleteWorkout(authentication.getName(), workoutId);
    }
}
