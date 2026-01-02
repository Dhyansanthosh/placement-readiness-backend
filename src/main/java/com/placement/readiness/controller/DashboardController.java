package com.placement.readiness.controller;

import com.placement.readiness.dto.DashboardResponse;
import com.placement.readiness.entity.UserProgress;
import com.placement.readiness.service.DashboardService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/api/dashboard/score")
    public DashboardResponse getScore(Authentication authentication) {

        // Logged-in user email (from JWT)
        String email = authentication.getName();

        UserProgress p = service.getProgress(email);

        int score = service.calculateScoreForUser(email);
        String status = service.getStatus(score);

        return new DashboardResponse(
                score,
                status,
                p.getDsaCompleted(),
                p.getCoreStatus(),
                p.getMockScore()
        );

    }
}
