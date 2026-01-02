package com.placement.readiness.controller;

import com.placement.readiness.dto.ProfileResponse;
import com.placement.readiness.service.DashboardService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final DashboardService service;

    public ProfileController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/api/profile")
    public ProfileResponse getProfile(Authentication authentication) {

        String email = authentication.getName(); // from JWT
        return service.getProfile(email);
    }
}
