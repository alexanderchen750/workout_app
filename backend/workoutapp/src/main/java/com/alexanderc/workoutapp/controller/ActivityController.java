package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.Activity;
import com.alexanderc.workoutapp.model.ActivityReq;
import com.alexanderc.workoutapp.model.ActivityResp;
import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/{userId}/workout/{workoutId}")
public class ActivityController {
    public final ActivityService activityService;
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/activity")
    public ActivityResp createActivity(@PathVariable Long userId, @PathVariable Long workoutId, @RequestBody ActivityReq activity) {
        return activityService.createActivity(userId, workoutId, activity);
    }
    @GetMapping("/activity")
    public List<ActivityResp> getActivity(@PathVariable Long userId, @PathVariable Long workoutId) {
        return activityService.getActivities(workoutId);
    }
    @DeleteMapping("/activity/{activityId}")
    public DeleteResp deleteActivity(@PathVariable Long activityId) {
        return activityService.deleteActivity(activityId);
    }
}
