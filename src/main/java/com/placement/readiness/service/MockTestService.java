package com.placement.readiness.service;

import com.placement.readiness.entity.MockTest;
import com.placement.readiness.entity.User;
import com.placement.readiness.repository.MockTestRepository;
import com.placement.readiness.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MockTestService {

    private final MockTestRepository mockRepo;
    private final UserRepository userRepo;

    public MockTestService(MockTestRepository mockRepo, UserRepository userRepo) {
        this.mockRepo = mockRepo;
        this.userRepo = userRepo;
    }

    public void addMockScore(String email, int score) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MockTest mock = new MockTest();
        mock.setUser(user);
        mock.setScore(score);
        mock.setTakenAt(LocalDateTime.now());

        mockRepo.save(mock);
    }

    public int getLatestMockScore(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<MockTest> mocks = mockRepo.findByUserOrderByTakenAtDesc(user);
        return mocks.isEmpty() ? 0 : mocks.get(0).getScore();
    }
    public List<MockTest> getMockHistory(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mockRepo.findByUserOrderByTakenAtDesc(user);
    }

}
