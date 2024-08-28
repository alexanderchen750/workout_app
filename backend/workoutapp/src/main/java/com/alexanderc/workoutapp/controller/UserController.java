package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.model.NameResp;
import com.alexanderc.workoutapp.model.User;
import com.alexanderc.workoutapp.model.IdResp;
import com.alexanderc.workoutapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user/login-details")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    public ResponseEntity<NameResp> loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NameResp nameResp = userService.getNameByEmail(authentication.getName());
        return ResponseEntity.ok(nameResp);
    }
}
