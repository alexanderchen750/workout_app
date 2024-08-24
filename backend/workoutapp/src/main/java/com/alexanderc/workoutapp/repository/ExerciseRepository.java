package com.alexanderc.workoutapp.repository;

import com.alexanderc.workoutapp.entity.ExerciseEntity;
import com.alexanderc.workoutapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    Optional<ExerciseEntity> findByNameAndCreatedByIsNull(String name); // For default exercises
    Optional<ExerciseEntity> findByNameAndCreatedBy(String name, UserEntity createdBy); // For user-created exercises
    @Query("SELECT e.name FROM ExerciseEntity e WHERE e.name LIKE %:search% AND (e.createdBy IS NULL OR e.createdBy = :user)")
    List<String> findExerciseNamesBySearchAndUser(@Param("search") String search, @Param("user") UserEntity user);
}

