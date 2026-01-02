package com.placement.readiness.dto;

public class DashboardResponse {

    private int score;
    private String status;
    private int dsaScore;
    private String coreStatus;
    private int mockScore;

    public DashboardResponse(
            int score,
            String status,
            int dsaScore,
            String coreStatus,
            int mockScore
    ) {
        this.score = score;
        this.status = status;
        this.dsaScore = dsaScore;
        this.coreStatus = coreStatus;
        this.mockScore = mockScore;
    }

    public int getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public int getDsaScore() {
        return dsaScore;
    }

    public String getCoreStatus() {
        return coreStatus;
    }

    public int getMockScore() {
        return mockScore;
    }
}
