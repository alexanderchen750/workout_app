package com.alexanderc.workoutapp.model;
import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.UserEntity;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


public class Workout {
    private Long id;
    private Date workoutDate;
    private LocalDateTime createdAt;
    private UserEntity user;
    private List<ActivityEntity> activies;
}
