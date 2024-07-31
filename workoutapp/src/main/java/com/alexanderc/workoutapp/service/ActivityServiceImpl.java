package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import com.alexanderc.workoutapp.model.Activity;
import com.alexanderc.workoutapp.repository.ActivityRepository;
import com.alexanderc.workoutapp.repository.WorkoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;
    private final WorkoutRepository workoutRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, WorkoutRepository workoutRepository){
        this.activityRepository = activityRepository;
        this.workoutRepository = workoutRepository;
    }


    @Override
    public ActivityEntity createActivity(Long workoutId) {

        Optional<WorkoutEntity> optionalWorkout = workoutRepository.findById(workoutId);

        if (!optionalWorkout.isPresent()) {
            return null; // Or handle this case in a way that's appropriate for your application
        }

        WorkoutEntity workout = optionalWorkout.get();

        ActivityEntity activity = new ActivityEntity();
        activity.setWorkout(workout);

        return activityRepository.save(activity);
    }
}
