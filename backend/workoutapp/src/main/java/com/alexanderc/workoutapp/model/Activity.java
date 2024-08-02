package com.alexanderc.workoutapp.model;
import lombok.Data;

import java.util.List;

@Data
public class Activity {
    private String exercise;
    private List<Set> sets;
}
