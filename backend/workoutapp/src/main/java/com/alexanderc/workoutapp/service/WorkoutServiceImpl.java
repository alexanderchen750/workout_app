package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import com.alexanderc.workoutapp.model.*;
import com.alexanderc.workoutapp.repository.UserRepository;
import com.alexanderc.workoutapp.repository.WorkoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final UserRepository userRepository;
    private final ActivityServiceImpl activityServiceImpl;
    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, UserRepository userRepository, ActivityServiceImpl activityServiceImpl) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.activityServiceImpl = activityServiceImpl;
    }

    @Autowired
    private ActivityService activityService;

    @Override
    public Long createWorkout(String email, WorkoutReq workoutReq) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        // Throw an exception if the user is not found
        UserEntity userEntity = userEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setUser(userEntity);
        if(workoutReq!=null){
            if(workoutReq.getDate()!=null){
                workoutEntity.setWorkoutDate(java.sql.Date.valueOf(workoutReq.getDate()));
            }
        }
        workoutEntity = workoutRepository.save(workoutEntity);
        return workoutEntity.getId();
    }

    @Override
    public List<WorkoutResp> getWorkoutIdsByUserId(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        // Throw an exception if the user is not found
        UserEntity userEntity = userEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return workoutRepository.findByUserId(userEntity.getId())
                .stream()
                .map(workoutEntity -> new WorkoutResp(
                        workoutEntity.getId(),
                        workoutEntity.getWorkoutDate().toString() // Assuming `getWorkoutDate` returns a LocalDate or Date object
                ))
                .collect(Collectors.toList());
    }



    @Override
    public DeleteResp deleteWorkout(String email, Long workoutId) {
        DeleteResp deleteResp = new DeleteResp();

        // Find the user by email
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            // Find the workout by ID
            Optional<WorkoutEntity> optionalWorkout = workoutRepository.findById(workoutId);
            if (optionalWorkout.isPresent()) {
                WorkoutEntity workout = optionalWorkout.get();

                // Check if the workout belongs to the user
                if (workout.getUser().equals(user)) {
                    // Delete the workout if it belongs to the user
                    workoutRepository.deleteById(workoutId);
                    deleteResp.setDeleted(true);
                    deleteResp.setId(workoutId);
                    return deleteResp;
                }
            }
        }
        deleteResp.setDeleted(false);
        return deleteResp;
    }

}
