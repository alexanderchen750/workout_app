package com.alexanderc.workoutapp.repository;

import com.alexanderc.workoutapp.entity.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {

}
