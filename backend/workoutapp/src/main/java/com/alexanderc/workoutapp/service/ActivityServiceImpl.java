package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.ExerciseEntity;
import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import com.alexanderc.workoutapp.model.ActivityReq;
import com.alexanderc.workoutapp.model.ActivityResp;
import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.model.SetResp;
import com.alexanderc.workoutapp.repository.ActivityRepository;
import com.alexanderc.workoutapp.repository.UserRepository;
import com.alexanderc.workoutapp.repository.WorkoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public ActivityServiceImpl(UserRepository userRepository, ActivityRepository activityRepository, WorkoutRepository workoutRepository){
        this.activityRepository = activityRepository;
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    @Autowired
    private SetService setService;

    @Autowired
    private ExerciseService exerciseService;

    @Override
    public ActivityResp createActivity(String email,Long workoutId,  ActivityReq activity) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        UserEntity userEntity = userEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        WorkoutEntity workoutEntity = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        // Get or create the exercise
        ExerciseEntity exerciseEntity = exerciseService.createExercise(userEntity.getId(), activity.getExercise());
        System.out.print(exerciseEntity.getName());
        // Create and save the activity entity
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setWorkout(workoutEntity);
        activityEntity.setExercise(exerciseEntity);
        activityEntity = activityRepository.save(activityEntity);

        final ActivityEntity finalActivityEntity = activityEntity;
        ActivityResp activityResp = new ActivityResp();
        List<SetResp> setRespList = new ArrayList<>();
        // Create sets
        if (activity.getSets() != null) {
            activity.getSets().forEach(set -> {
                SetResp setResp = setService.createSet(finalActivityEntity.getId(), set);
                setRespList.add(setResp);
            });
        }

        activityResp.setId(activityEntity.getId());
        activityResp.setExercise(activityEntity.getExercise().getName());
        activityResp.setSets(setRespList);
        return activityResp;
    }

    @Override
    public List<ActivityResp> getActivities(Long workoutId) {
        WorkoutEntity workoutEntity = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        List<ActivityEntity> activityEntities = activityRepository.findByWorkout(workoutEntity);

        // Convert each ActivityEntity to ActivityResp
        return activityEntities.stream()
                .map(this::convertToActivityResp)
                .collect(Collectors.toList());
    }

    private ActivityResp convertToActivityResp(ActivityEntity activityEntity) {
        ActivityResp activityResp = new ActivityResp();
        activityResp.setId(activityEntity.getId());
        activityResp.setExercise(activityEntity.getExercise().getName());

        List<SetResp> setRespList = activityEntity.getSets().stream()
                .map(set -> new SetResp(set.getId(), set.getReps(), set.getWeight()))
                .collect(Collectors.toList());

        activityResp.setSets(setRespList);
        return activityResp;
    }

    public DeleteResp deleteActivity(Long activityId) {
        DeleteResp deleteResp = new DeleteResp();
        Optional<ActivityEntity> activityEntity = activityRepository.findById(activityId);
        if(activityEntity.isPresent()){
            activityRepository.deleteById(activityId);
            deleteResp.setId(activityId);
            deleteResp.setDeleted(true);
            return deleteResp;
        }
        deleteResp.setDeleted(false);
        return deleteResp;

    }
}
