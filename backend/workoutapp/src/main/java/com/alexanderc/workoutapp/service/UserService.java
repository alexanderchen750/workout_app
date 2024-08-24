package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.model.NameResp;
import com.alexanderc.workoutapp.model.User;

public interface UserService {
    Long createUser(User user);
    NameResp getNameByEmail(String email);
}
