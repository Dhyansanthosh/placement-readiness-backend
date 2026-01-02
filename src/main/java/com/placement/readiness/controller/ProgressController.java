package com.placement.readiness.controller;

import com.placement.readiness.dto.UpdateProgressRequest;
import com.placement.readiness.entity.User;
import com.placement.readiness.repository.UserRepository;
import com.placement.readiness.service.ProgressService;
import com.placement.readiness.dto.ProgressRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService service;

    public ProgressController(ProgressService service) {
        this.service = service;
    }

    @PutMapping
    public void updateProgress(
            Authentication authentication,
            @Valid @RequestBody UpdateProgressRequest req
    ) {
        String email = authentication.getName(); // from JWT
        service.updateProgress(email, req);
    }
}
