package com.placement.readiness.controller;

import com.placement.readiness.entity.MockTest;
import com.placement.readiness.service.MockTestService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mocks")
public class MockTestController {

    private final MockTestService service;

    public MockTestController(MockTestService service) {
        this.service = service;
    }

    @PostMapping
    public void addMock(Authentication auth, @RequestParam int score) {
        service.addMockScore(auth.getName(), score);
    }
    @GetMapping
    public List<MockTest> getMockHistory(Authentication auth) {
        return service.getMockHistory(auth.getName());
    }

}
