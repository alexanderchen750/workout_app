package com.alexanderc.workoutapp.model;

import lombok.Data;

@Data
public class IdResp {
    private Long id;

    public IdResp(Long userId) {
        this.id = userId;
    }
}
