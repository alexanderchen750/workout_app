package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.SetEntity;
import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.model.SetReq;
import com.alexanderc.workoutapp.model.SetResp;

public interface SetService {
    public SetResp createSet(Long activityId, SetReq set);
    public SetResp createProtectedSet(Long userId, Long workoutId, Long activityId, SetReq set);
    public DeleteResp deleteSet(Long setId);
    public Boolean checkSet(Long activityId, Long workoutId, Long userId);
}
