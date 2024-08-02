package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.SetEntity;
import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import com.alexanderc.workoutapp.model.ActivityReq;
import com.alexanderc.workoutapp.model.DeleteResp;
import com.alexanderc.workoutapp.model.SetReq;
import com.alexanderc.workoutapp.model.SetResp;
import com.alexanderc.workoutapp.repository.ActivityRepository;
import com.alexanderc.workoutapp.repository.SetRepository;
import com.alexanderc.workoutapp.repository.UserRepository;
import com.alexanderc.workoutapp.repository.WorkoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {
    private SetRepository setRepository;
    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public SetResp createSet(Long activityId, SetReq setReq) {
        ActivityEntity activityEntity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        SetEntity setEntity = new SetEntity();
        setEntity.setActivity(activityEntity);
        setEntity.setReps(setReq.getReps());
        setEntity.setWeight(setReq.getWeight());
        setEntity = setRepository.save(setEntity);

        SetResp setResp = new SetResp();
        setResp.setReps(setEntity.getReps());
        setResp.setWeight(setEntity.getWeight());
        setResp.setId(setEntity.getId());
        return setResp;
    }

    @Override
    public SetResp createProtectedSet(Long userId, Long workoutId, Long activityId, SetReq setReq) {
        if(checkSet(userId, workoutId, activityId)){
            return createSet(activityId, setReq);
        }
        else{
            throw new RuntimeException("User, workout, or activity not found");
        }
    }
    @Override
    public DeleteResp deleteSet(Long setId){
        Optional<SetEntity> setEntity = setRepository.findById(setId);
        DeleteResp deleteResp = new DeleteResp();
        if(setEntity.isPresent()){
            setRepository.deleteById(setId);
            deleteResp.setDeleted(true);
            deleteResp.setId(setId);
            return deleteResp;
        }
        deleteResp.setDeleted(false);
        return deleteResp;
    }

    @Override
    public Boolean checkSet(Long userId, Long workoutId, Long activityId) {
        // Fetch the user and their workouts
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            return false; // User not found
        }

        // Check if the workout belongs to the user
        WorkoutEntity workoutEntity = userEntity.getWorkouts().stream()
                .filter(workout -> workout.getId().equals(workoutId))
                .findFirst()
                .orElse(null);
        if (workoutEntity == null) {
            return false; // Workout not found for the user
        }

        // Check if the activity belongs to the workout
        ActivityEntity activityEntity = workoutEntity.getActivities().stream()
                .filter(activity -> activity.getId().equals(activityId))
                .findFirst()
                .orElse(null);
        if (activityEntity == null) {
            return false; // Activity not found for the workout
        }

        return true; // All checks passed
    }

}
