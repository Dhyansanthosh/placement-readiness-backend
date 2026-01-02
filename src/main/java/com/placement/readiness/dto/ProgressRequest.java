package com.placement.readiness.dto;

public record ProgressRequest(
        int dsaCompleted,
        int dsaTotal,
        int mockScore,
        String coreStatus
) {}
