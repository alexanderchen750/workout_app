package com.alexanderc.workoutapp.model;

import lombok.Data;

import java.sql.Date;

@Data
public class WorkoutResp {
    private Long id;
    private String date;

    public WorkoutResp(Long id, String date) {
        this.id = id;
        this.date = date;
    }
}
