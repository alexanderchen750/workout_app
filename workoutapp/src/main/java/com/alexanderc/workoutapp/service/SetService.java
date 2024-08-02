package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.SetEntity;
import com.alexanderc.workoutapp.model.SetReq;
import com.alexanderc.workoutapp.model.SetResp;

public interface SetService {
    public SetResp createSet(Long activityId, SetReq set);
}
