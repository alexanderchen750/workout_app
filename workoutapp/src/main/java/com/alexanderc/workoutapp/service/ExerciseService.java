package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ExerciseEntity;
import com.alexanderc.workoutapp.model.Exercise;

public interface ExerciseService {
    ExerciseEntity createExercise(Long userId,String exercise);
}


