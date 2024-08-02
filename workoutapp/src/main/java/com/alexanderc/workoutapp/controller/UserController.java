package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.User;
import com.alexanderc.workoutapp.model.IdResp;
import com.alexanderc.workoutapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    public ResponseEntity<IdResp> createUser(@RequestBody User user) {
        Long userId = userService.createUser(user);
        // Return a ResponseEntity with status 201 Created and a body containing the userId
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdResp(userId));
    }
}
