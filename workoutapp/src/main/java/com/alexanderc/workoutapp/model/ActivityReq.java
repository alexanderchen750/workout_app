package com.alexanderc.workoutapp.model;

import lombok.Data;

import java.util.List;

@Data
public class ActivityReq {
    private String exercise;
    private List<SetReq> sets;
}
