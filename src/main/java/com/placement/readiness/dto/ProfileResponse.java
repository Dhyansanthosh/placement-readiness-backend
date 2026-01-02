package com.placement.readiness.dto;

public class ProfileResponse {

    private String email;
    private int dsaCompleted;
    private int dsaTotal;
    private int mockScore;
    private String coreStatus;

    public ProfileResponse(
            String email,
            int dsaCompleted,
            int dsaTotal,
            int mockScore,
            String coreStatus
    ) {
        this.email = email;
        this.dsaCompleted = dsaCompleted;
        this.dsaTotal = dsaTotal;
        this.mockScore = mockScore;
        this.coreStatus = coreStatus;
    }

    public String getEmail() { return email; }
    public int getDsaCompleted() { return dsaCompleted; }
    public int getDsaTotal() { return dsaTotal; }
    public int getMockScore() { return mockScore; }
    public String getCoreStatus() { return coreStatus; }
}
