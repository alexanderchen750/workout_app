package com.alexanderc.workoutapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="activities")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private WorkoutEntity workout;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SetEntity> sets;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;


}
