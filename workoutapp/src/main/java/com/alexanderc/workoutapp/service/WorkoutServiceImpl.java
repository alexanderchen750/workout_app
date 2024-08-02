package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import com.alexanderc.workoutapp.model.ActivityResp;
import com.alexanderc.workoutapp.model.Workout;
import com.alexanderc.workoutapp.repository.UserRepository;
import com.alexanderc.workoutapp.repository.WorkoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final UserRepository userRepository;
    private final ActivityServiceImpl activityServiceImpl;
    private WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, UserRepository userRepository, ActivityServiceImpl activityServiceImpl) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.activityServiceImpl = activityServiceImpl;
    }

    @Autowired
    private ActivityService activityService;

    @Override
    public Long createWorkout(Long userId) {

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setUser(userEntity);
        workoutEntity = workoutRepository.save(workoutEntity);
        return workoutEntity.getId();
        /*
        WorkoutEntity finalWorkoutEntity = workoutEntity;
        // Create activities
        if (workout.getActivities() != null) {
            workout.getActivities().forEach(activity ->
                    activityService.createActivity(finalWorkoutEntity.getId(), userId, activity));
        }
        Workout workoutDTO = new Workout();
        BeanUtils.copyProperties(workoutEntity, workoutDTO);
        return workoutDTO;
         */
    }

    @Override
    public List<Long> getWorkoutIdsByUserId(Long userId){
        return workoutRepository.findByUserId(userId)
                .stream()
                .map(WorkoutEntity::getId)
                .collect(Collectors.toList());
    }
}
