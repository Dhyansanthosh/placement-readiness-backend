package com.placement.readiness.repository;

import com.placement.readiness.entity.User;
import com.placement.readiness.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProgressRepository
        extends JpaRepository<UserProgress, Long> {

    Optional<UserProgress> findByUser(User user);
}