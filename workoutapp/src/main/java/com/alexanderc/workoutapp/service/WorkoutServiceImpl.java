package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import com.alexanderc.workoutapp.model.Workout;
import com.alexanderc.workoutapp.repository.UserRepository;
import com.alexanderc.workoutapp.repository.WorkoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final UserRepository userRepository;
    private WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long createWorkout(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setUser(user);
        WorkoutEntity savedWorkout = workoutRepository.save(workoutEntity);
        return savedWorkout.getId();
    }

    @Override
    public List<Long> getWorkoutIdsByUserId(Long userId){
        return workoutRepository.findByUserId(userId)
                .stream()
                .map(WorkoutEntity::getId)
                .collect(Collectors.toList());
    }
}
