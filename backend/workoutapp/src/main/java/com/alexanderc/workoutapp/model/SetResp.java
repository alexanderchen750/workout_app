package com.alexanderc.workoutapp.model;

import lombok.Data;

@Data
public class SetResp {
    private Long id;
    private int reps;
    private int weight;

    public SetResp(){
    }

    public SetResp(Long id, int reps, int weight) {
        this.id = id;
        this.reps = reps;
        this.weight = weight;
    }
}
