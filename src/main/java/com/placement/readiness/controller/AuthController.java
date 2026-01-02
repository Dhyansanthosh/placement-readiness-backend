package com.placement.readiness.controller;

import com.placement.readiness.dto.LoginRequest;
import com.placement.readiness.service.UserService;
import com.placement.readiness.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest req) {
        try {
            service.register(req.getEmail(), req.getPassword());
            return ResponseEntity.ok("REGISTERED");
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        boolean valid = service.login(req.getEmail(), req.getPassword());

        if (!valid) {
            return ResponseEntity.status(401).body("INVALID_CREDENTIALS");
        }

        String token = JwtUtil.generateToken(req.getEmail());
        return ResponseEntity.ok(token);
    }

}
