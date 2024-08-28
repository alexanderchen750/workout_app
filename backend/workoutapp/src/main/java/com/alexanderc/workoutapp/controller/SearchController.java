package com.alexanderc.workoutapp.controller;

import com.alexanderc.workoutapp.repository.ExerciseRepository;
import com.alexanderc.workoutapp.service.ExerciseService;
import com.alexanderc.workoutapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SearchController {

    private final ExerciseService exerciseService;
    public SearchController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercise/search")
    @CrossOrigin(origins = {"http://localhost:3000", "http://workout-app-frontend-1.s3-website-us-west-1.amazonaws.com"})
    public List<String> searchExerciseNames(@RequestParam String search) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return exerciseService.searchExerciseNames(authentication.getName(), search);
    }


}
