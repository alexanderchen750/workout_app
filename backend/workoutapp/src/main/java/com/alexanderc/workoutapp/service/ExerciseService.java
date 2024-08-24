package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ExerciseEntity;
import com.alexanderc.workoutapp.model.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseEntity createExercise(Long userId,String exercise);
    List<String> searchExerciseNames(String email, String search);
}


