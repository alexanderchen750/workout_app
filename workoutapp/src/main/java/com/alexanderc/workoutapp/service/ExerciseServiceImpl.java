package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ExerciseEntity;
import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.model.Exercise;
import com.alexanderc.workoutapp.repository.ExerciseRepository;
import com.alexanderc.workoutapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }
    @Autowired
    private UserRepository userRepository;

    @Override
    public ExerciseEntity createExercise(Long UserId, String exercise){
        UserEntity userEntity = userRepository.findById(UserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the exercise already exists
        ExerciseEntity existingExercise = exerciseRepository.findByNameAndCreatedByIsNull(exercise)
                .orElse(exerciseRepository.findByNameAndCreatedBy(exercise, userEntity).orElse(null));

        if (existingExercise != null) {
            return existingExercise;
        }

        // Create new exercise
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setName(exercise);
        exerciseEntity.setCreatedBy(userEntity);
        return exerciseRepository.save(exerciseEntity);
    }
}
