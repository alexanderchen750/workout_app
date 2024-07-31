package com.alexanderc.workoutapp.model;

import com.alexanderc.workoutapp.entity.ExerciseEntity;
import com.alexanderc.workoutapp.entity.SetEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;

import java.util.List;

public class Activity {
    private Long id;
    private WorkoutEntity workout;
    private List<SetEntity> sets;
    private ExerciseEntity exercise;
}
