package com.alexanderc.workoutapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="workouts")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workout_date")
    private Date workoutDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ActivityEntity> activities;

    @PrePersist
    protected void onCreate() {
        if (workoutDate == null) {
            LocalDate localDate = LocalDate.now();
            workoutDate = Date.valueOf(localDate);
        }
    }
}
