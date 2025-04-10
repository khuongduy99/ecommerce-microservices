package com.ms.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.authservice.dto.AuthRequest;
import com.ms.authservice.dto.AuthResponse;
import com.ms.authservice.dto.RegisterRequest;
import com.ms.authservice.entity.User;
import com.ms.authservice.repository.UserRepository;
import com.ms.authservice.security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(encoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();

        repo.save(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        User user = repo.findByUsername(req.getUsername()).orElseThrow();
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(403).build();
        }

        String token = jwt.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}