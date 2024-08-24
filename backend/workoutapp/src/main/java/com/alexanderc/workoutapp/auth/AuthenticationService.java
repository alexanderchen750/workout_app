package com.alexanderc.workoutapp.auth;

import com.alexanderc.workoutapp.config.JwtService;
import com.alexanderc.workoutapp.entity.Role;
import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.exeception.EmailAlreadyExistsException;
import com.alexanderc.workoutapp.exeception.InvalidCredentialsException;
import com.alexanderc.workoutapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResp register(RegisterReq request) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email is already in use");
        }

        // Proceed with registration
        var user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResp.builder()
                .token(jwtToken)
                .name(user.getName())
                .build();
    }

    public AuthenticationResp authentication(AuthenticationReq request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResp.builder()
                    .token(jwtToken)
                    .name(user.getName())
                    .build();
        } catch (AuthenticationException e){
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
}
