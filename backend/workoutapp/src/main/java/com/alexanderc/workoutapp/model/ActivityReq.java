package com.alexanderc.workoutapp.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ActivityReq {
    private Date date;
    private String exercise;
    private List<SetReq> sets;
}
