package com.alexanderc.workoutapp.model;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class WorkoutReq {
    private LocalDate date;
}
