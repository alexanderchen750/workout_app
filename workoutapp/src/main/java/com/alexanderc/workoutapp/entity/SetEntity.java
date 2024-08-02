package com.alexanderc.workoutapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="sets")
public class SetEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int reps;
    private int weight;

    @ManyToOne()
    @JoinColumn(name = "activity_id")
    private ActivityEntity activity;
}
