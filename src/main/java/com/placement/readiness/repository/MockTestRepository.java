package com.placement.readiness.repository;

import com.placement.readiness.entity.MockTest;
import com.placement.readiness.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MockTestRepository extends JpaRepository<MockTest, Long> {
    List<MockTest> findByUserOrderByTakenAtDesc(User user);
}
