package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.model.Activity;

public interface ActivityService {
    ActivityEntity createActivity(Long workoutId);
}
