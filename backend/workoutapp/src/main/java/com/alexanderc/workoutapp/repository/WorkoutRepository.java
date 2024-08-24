package com.alexanderc.workoutapp.repository;

import com.alexanderc.workoutapp.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {
    @Query("SELECT w FROM WorkoutEntity w WHERE w.user.id = :userId ORDER BY w.workoutDate DESC ")
    List<WorkoutEntity> findByUserId(@Param("userId") Long userId);
}
