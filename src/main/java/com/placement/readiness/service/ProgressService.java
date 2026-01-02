package com.placement.readiness.service;

import com.placement.readiness.dto.UpdateProgressRequest;
import com.placement.readiness.entity.User;
import com.placement.readiness.entity.UserProgress;
import com.placement.readiness.repository.UserProgressRepository;
import com.placement.readiness.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class ProgressService {

    private final UserRepository userRepository;
    private final UserProgressRepository progressRepository;

    public ProgressService(UserRepository userRepository,
                           UserProgressRepository progressRepository) {
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
    }

    public void updateProgress(String email, UpdateProgressRequest req) {

        // 1️⃣ EMAIL → USER
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ USER → PROGRESS
        UserProgress progress = progressRepository.findByUser(user)
                .orElse(new UserProgress());

        // 3️⃣ SET ENTITY FIELDS
        progress.setUser(user); // ENTITY METHOD
        progress.setDsaCompleted(req.getDsaCompleted());
        progress.setDsaTotal(req.getDsaTotal());
        progress.setMockScore(req.getMockScore());
        progress.setCoreStatus(req.getCoreStatus());
        if (req.getDsaCompleted() > req.getDsaTotal()) {
            throw new RuntimeException("DSA completed cannot exceed total");
        }
        // 4️⃣ SAVE
        progressRepository.save(progress);
    }
}
