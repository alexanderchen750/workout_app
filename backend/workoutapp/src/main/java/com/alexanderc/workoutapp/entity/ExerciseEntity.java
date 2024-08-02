package com.alexanderc.workoutapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name="exercises")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = true)
    private UserEntity createdBy; // Can be null for default exercises

}
