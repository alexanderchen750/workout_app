package com.alexanderc.workoutapp.service;
import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.model.ActivityReq;
import com.alexanderc.workoutapp.model.ActivityResp;

import java.util.List;

public interface ActivityService {
    ActivityResp createActivity( Long userId, Long workoutId, ActivityReq activity);
    List<ActivityResp> getActivities(Long workoutId);
}
