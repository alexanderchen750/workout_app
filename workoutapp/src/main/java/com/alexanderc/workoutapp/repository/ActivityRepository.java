package com.alexanderc.workoutapp.repository;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findByWorkout(WorkoutEntity workout);
}
