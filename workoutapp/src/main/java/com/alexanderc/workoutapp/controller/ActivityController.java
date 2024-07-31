package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.Activity;
import com.alexanderc.workoutapp.service.ActivityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    public final ActivityService activityService;
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    /*
    @PostMapping("/create")
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }*/
}
