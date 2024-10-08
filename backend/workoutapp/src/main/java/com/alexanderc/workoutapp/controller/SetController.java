package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.model.SetReq;
import com.alexanderc.workoutapp.model.SetResp;
import com.alexanderc.workoutapp.service.SetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workout/{workoutId}/activity/{activityId}")
public class SetController {

    public final SetService setService;
    public SetController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping("/set")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    public SetResp createSet(@PathVariable Long userId, @PathVariable Long workoutId, @PathVariable Long activityId, @RequestBody SetReq setReq) {
        return setService.createProtectedSet(userId, workoutId, activityId, setReq);
    }

    @DeleteMapping("/set/{setId}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})

    public DeleteResp deleteSet(@PathVariable Long setId){
        return setService.deleteSet(setId);
    }

}
