package com.alexanderc.workoutapp.model;

import lombok.Data;

@Data
public class DeleteResp {
    private boolean deleted;
    private Long id;
}
