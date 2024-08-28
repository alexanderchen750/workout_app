package com.alexanderc.workoutapp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResp> register(@RequestBody RegisterReq request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping ("/authenticate")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    public ResponseEntity<AuthenticationResp> authenticate(@RequestBody AuthenticationReq request) {
        return ResponseEntity.ok(service.authentication(request));
    }



}
