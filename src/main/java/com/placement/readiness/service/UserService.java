package com.placement.readiness.service;

import com.placement.readiness.entity.User;
import com.placement.readiness.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.placement.readiness.entity.UserProgress;
import com.placement.readiness.repository.UserProgressRepository;
@Service
public class UserService {
    private final UserProgressRepository progressRepository;
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository,
                       BCryptPasswordEncoder encoder,
                       UserProgressRepository progressRepository) {
        this.repository = repository;
        this.encoder = encoder;
        this.progressRepository = progressRepository;
    }

    public void register(String email, String password) {

        if (repository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // 1️⃣ Save user
        User user = new User();
        user.setEmail(email);
        user.setPassword(encoder.encode(password));

        User savedUser = repository.save(user);

        // 2️⃣ Auto-create progress
        UserProgress progress = new UserProgress();
        progress.setUser(savedUser);
        progress.setDsaCompleted(0);
        progress.setDsaTotal(0);
        progress.setMockScore(0);
        progress.setCoreStatus("LEARNING");

        progressRepository.save(progress);
    }

    public boolean login(String email, String password) {

        return repository.findByEmail(email)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}