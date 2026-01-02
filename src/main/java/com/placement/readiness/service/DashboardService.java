package com.placement.readiness.service;

import com.placement.readiness.dto.ProfileResponse;
import com.placement.readiness.entity.User;
import com.placement.readiness.entity.UserProgress;
import com.placement.readiness.repository.UserProgressRepository;
import com.placement.readiness.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final UserProgressRepository progressRepository;

    private final MockTestService mockTestService;

    public DashboardService(UserRepository userRepository,
                            UserProgressRepository progressRepository,
                            MockTestService mockTestService) {
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
        this.mockTestService = mockTestService;
    }

    public UserProgress getProgress(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return progressRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    public int calculateScoreForUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProgress p = progressRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        int dsaScore = 0;
        if (p.getDsaTotal() > 0) {
            dsaScore = (p.getDsaCompleted() * 40) / p.getDsaTotal();
        }
        int coreScore = switch (p.getCoreStatus()) {
            case "CONFIDENT" -> 25;
            case "REVISING" -> 20;
            default -> 15;
        };
        int mockScore = mockTestService.getLatestMockScore(email);

        return dsaScore + coreScore + mockScore;
    }

    public String getStatus(int score) {
        if (score >= 80) return "Ready";
        if (score >= 60) return "Almost Ready";
        if (score >= 40) return "Foundation Weak";
        return "Not Ready";
    }

    public ProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProgress p = progressRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        return new ProfileResponse(
                user.getEmail(),
                p.getDsaCompleted(),
                p.getDsaTotal(),
                p.getMockScore(),
                p.getCoreStatus()
        );
    }
}
