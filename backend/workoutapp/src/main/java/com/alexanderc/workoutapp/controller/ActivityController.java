package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.Activity;
import com.alexanderc.workoutapp.model.ActivityReq;
import com.alexanderc.workoutapp.model.ActivityResp;
import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.service.ActivityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workout/{workoutId}")
public class ActivityController {
    public final ActivityService activityService;
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    @PostMapping("/activity")
    public ActivityResp createActivity( @PathVariable Long workoutId, @RequestBody ActivityReq activity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return activityService.createActivity(authentication.getName(), workoutId, activity);
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    @GetMapping("/activity")
    public List<ActivityResp> getAllActivities(@PathVariable Long workoutId) {
        return activityService.getActivities(workoutId);
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    @DeleteMapping("/activity/{activityId}")
    public DeleteResp deleteActivity(@PathVariable Long activityId) {
        return activityService.deleteActivity(activityId);
    }
}
