package com.alexanderc.workoutapp.model;

import com.alexanderc.workoutapp.entity.SetEntity;
import lombok.Data;

import java.util.List;

@Data
public class ActivityResp {
    private Long id;
    private String exercise;
    private List<SetResp> sets;
}
